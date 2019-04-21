package com.example.ast.data.remote

import android.content.Context
import com.dbnow.hw.utils.ScreenUtil
import com.example.ast.data.local.GlobalSettings
import com.example.ast.data.remote.models.Login
import com.example.ast.data.remote.models.Notification
import com.example.ast.data.remote.models.Response
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.HashMap

class HttpHelper(val context: Context,val globalSettings: GlobalSettings, private val astHelper: AstHelper) {

    fun login(login: String, password: String): Single<Response<Login>> {
        val jsonString = "{\"name\":\"${login}\",\"pass\":\"${password}\"}"
        val queryMap = object : HashMap<String, String>() {
            init {
                put("test.getUsers", jsonString)
            }
        }

        return astHelper.login(queryMap)
    }

    fun updatePushToken(gcm: Notification): Single<Any> {
        val jsonString = "{\"Bearer\":\"${globalSettings.token}\"}"
        val queryMap = object : HashMap<String, String>() {
            init {
                put("test.addHeader", jsonString)
            }
        }

        return astHelper.updatePushToken(queryMap, gcm)
    }
}