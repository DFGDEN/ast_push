package com.example.ast

import android.app.Application
import android.content.Context
import com.example.ast.injection.component.ApplicationComponent
import com.example.ast.injection.component.DaggerApplicationComponent
import com.example.ast.injection.module.ApplicationModule
import com.example.ast.injection.module.GsonModule
import com.example.ast.injection.module.HttpModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(HttpModule(BASE_HTTP_URL))
                .gsonModule(GsonModule())
                .build()
        }
    }


    companion object {

        const val BASE_HTTP_URL = "http://178.124.158.226:9784/"

        private var mApplicationComponent: ApplicationComponent? = null

        var showBanner = true

        fun getApplicationComponent(): ApplicationComponent? {
            return mApplicationComponent
        }

        fun get(context: Context): App {
            return context.applicationContext as App
        }

    }
}