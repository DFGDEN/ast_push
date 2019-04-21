package com.example.ast.injection.module


import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.ast.data.DataManager
import com.example.ast.data.local.GlobalSettings
import com.example.ast.data.remote.HttpHelper
import com.example.ast.injection.qualifier.ApplicationContext


import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication.applicationContext
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        return mApplication.resources
    }

    @Provides
    @Singleton
    internal fun provideGlobalSetting(@ApplicationContext context: Context): GlobalSettings {
        return GlobalSettings(context)
    }

    @Provides
    @Singleton
    fun provideDataManager(
        httpHelper: HttpHelper
    ): DataManager {
        return DataManager(httpHelper)
    }



}
