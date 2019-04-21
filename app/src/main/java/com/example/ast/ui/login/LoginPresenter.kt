package com.example.ast.ui.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dbnow.hw.utils.RxUtil
import com.example.ast.App
import com.example.ast.data.DataManager
import com.example.ast.data.local.GlobalSettings
import com.example.ast.data.remote.models.Login
import com.example.ast.data.remote.models.Notification
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {


    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var globalSettings: GlobalSettings


    private var loginDisposable: Disposable? = null

    init {
        App.getApplicationComponent()!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (globalSettings.token != null) {
            viewState.succesRegistration()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtil.unsubscribe(loginDisposable)
    }

    fun login(login: String, password: String) {
        viewState.onProgress(true, "Регистриация...")
        RxUtil.unsubscribe(loginDisposable)
        loginDisposable = dataManager.login(login, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({



                viewState.onProgress(false, "")
                try {
                    globalSettings.saveEmail(it.response.email)
                    globalSettings.savePassword(password)
                    globalSettings.saveToken(it.response.token)
                    updatePushToken()
                    viewState.succesRegistration()
                } catch (e: Exception) {
                    viewState.onError("Ошибка при регистрации")
                }
            }, {
                viewState.onProgress(false, "")
                viewState.onError("Ошибка при регистрации")
            })
    }

   private fun updatePushToken(){
       val pushToken = FirebaseInstanceId.getInstance().token ?: return
       loginDisposable = dataManager.updatePushToken(Notification(pushToken))
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .subscribe({
           }, {

           })
   }
}