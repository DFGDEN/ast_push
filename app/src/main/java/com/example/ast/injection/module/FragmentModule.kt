package com.example.ast.injection.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.example.ast.injection.qualifier.ActivityContext



import dagger.Module
import dagger.Provides


@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
     fun provideActivity(): Activity {
        return this.fragment.activity!!
    }

    @Provides
    @ActivityContext
     fun provideActivityContext(): Context {
        return this.fragment.activity!!
    }
}
