package com.example.ast.injection.module

import android.app.Activity
import android.app.Fragment
import android.content.Context
import com.example.ast.injection.qualifier.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class DialogFragmentModule(private val fragment: Fragment) {

    @Provides
     fun provideActivity(): Activity {
        return this.fragment.activity
    }

    @Provides
    @ActivityContext
     fun provideActivityContext(): Context {
        return this.fragment.activity
    }
}