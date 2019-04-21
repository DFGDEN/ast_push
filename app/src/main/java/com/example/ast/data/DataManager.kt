package com.example.ast.data

import com.example.ast.data.remote.HttpHelper
import com.example.ast.data.remote.models.Login
import com.example.ast.data.remote.models.Notification
import com.example.ast.data.remote.models.Response
import io.reactivex.Single

class DataManager(val httpHelper: HttpHelper) {

    fun login(login: String, password: String)  : Single<Response<Login>> {
        return httpHelper.login(login, password)
    }

    fun updatePushToken(gcm: Notification): Single<Any> {
        return httpHelper.updatePushToken(gcm)
    }
}