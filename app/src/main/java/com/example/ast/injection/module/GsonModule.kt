package com.example.ast.injection.module

import com.google.gson.*
import dagger.Module
import dagger.Provides
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

/**
 * Created by dfgden on 3/9/17.
 */
@Module
class GsonModule {

    private val dateFormat = object : SimpleDateFormat(DATE_FORMAT) {
        init {
            this.isLenient = false
            this.timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat(DATE_FORMAT)
        gsonBuilder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, typeOfT, context ->
            try {
                return@JsonDeserializer synchronized(dateFormat) {
                    dateFormat.parse(json.asString)
                }
            } catch (e: ParseException) {
                throw JsonParseException(e)
            }
        })
        return gsonBuilder.create()
    }

    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    }
}
