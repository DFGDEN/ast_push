package com.example.ast.injection.module


import android.content.Context
import android.util.Log
import com.example.ast.data.local.GlobalSettings
import com.example.ast.data.remote.AstHelper
import com.example.ast.data.remote.HttpHelper
import com.example.ast.injection.qualifier.ApplicationContext
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class HttpModule(private val mBaseUrl: String) {

    companion object {
        private const val URL_USER_GENERATE_PREFIX = "user-gen/"
        private const val URL_TOKEN_PREFIX = "jwt/create/"
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(globalSetting: GlobalSettings): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(35, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor { chain ->
            val original = chain.request()

            var request = original.newBuilder()
                .addHeader("Authorization", "Bearer " + globalSetting.token)
                .method(original.method(), original.body())
                .build()

            try {
                var response = chain.proceed(request)
                if (response.code() == 401) {

                    val requestUserGenerate = Request.Builder()
                        .url(mBaseUrl + URL_USER_GENERATE_PREFIX)
                        .post(RequestBody.create(null, ""))
                        .build()

                    val userGenerateResponse = OkHttpClient()
                        .newCall(requestUserGenerate)
                        .execute()
                    if (userGenerateResponse.isSuccessful) {
                        val responseBody = JSONObject(userGenerateResponse.body()?.string())
                        val email = responseBody.getString("email")
                        val password = responseBody.getString("password")
                        globalSetting.saveEmail(email)
                        globalSetting.savePassword(password)
                        val formBuilder = FormBody.Builder()
                            .add("email", email)
                            .add("password", password)
                        val formBody = formBuilder.build()
                        val requestAccessToken = Request.Builder()
                            .url(mBaseUrl + URL_TOKEN_PREFIX)
                            .post(formBody)
                            .build()
                        val accessTokenResponse = OkHttpClient()
                            .newCall(requestAccessToken)
                            .execute()


                        if (accessTokenResponse.isSuccessful) {

                            val responseBody = JSONObject(accessTokenResponse.body()?.string())
                            val accessToken = responseBody.getString("token")
                            globalSetting.saveToken(accessToken)
                        }

                    }

                    request = original.newBuilder()
                        .addHeader("Authorization", "Bearer " + globalSetting.token)
                        .method(original.method(), original.body())
                        .build()
                    response = chain.proceed(request)
                }

                response
            } catch (e: Exception) {
                Log.e("mym", "http : " + e?.message)
                null
            }

        }

        client.addInterceptor(logging)
        return client.build()

    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideHttpHelper(@ApplicationContext context: Context, retrofit: Retrofit, globalSettings: GlobalSettings): HttpHelper {
        return HttpHelper(context,globalSettings ,retrofit.create(AstHelper::class.java))
    }
}
