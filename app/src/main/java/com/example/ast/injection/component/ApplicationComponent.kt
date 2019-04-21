package com.example.ast.injection.component


import android.app.Application
import android.content.res.Resources
import com.example.ast.data.ASTNotificationService
import com.example.ast.data.DataManager
import com.example.ast.injection.module.*
import com.example.ast.ui.login.LoginPresenter
import com.example.ast.ui.main.MainPresenter

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class, HttpModule::class, GsonModule::class))
interface ApplicationComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

    fun dialogFragmentComponent(fragmentModule: DialogFragmentModule): DialogFragmentComponent

    fun inject(service: ASTNotificationService)

    fun inject(presenter: LoginPresenter)

    fun inject(presenter: MainPresenter)

//    fun inject(presenter: SettingsPresenter)
//
//    fun inject(presenter: WallpapersPresenter)
//
//    fun inject(presenter: ThemesPresenter)
//
//    fun inject(view: LocalMediaViewer)


    fun resources(): Resources

    fun application(): Application

    fun dataManager(): DataManager




}
