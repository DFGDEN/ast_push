package com.example.ast.injection.module

import android.app.Activity
import android.content.Context
import com.example.ast.injection.qualifier.ActivityContext


import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }
}
