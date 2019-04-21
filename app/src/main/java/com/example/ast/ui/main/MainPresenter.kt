package com.example.ast.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.ast.App
import com.example.ast.data.DataManager
import com.example.ast.data.local.GlobalSettings
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var globalSettings: GlobalSettings


    private var loginDisposable: Disposable? = null

    init {
        App.getApplicationComponent()!!.inject(this)
    }

    fun  logout(){
        Thread {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId()
            } catch (e: Exception) {
                //note
            }
        }.start()
        globalSettings.logout()
        viewState.logout()
    }

}