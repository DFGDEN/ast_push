package com.example.ast.data.local

import android.content.Context

class GlobalSettings(val context: Context) {
    val preferenceHelper = PreferenceHelper(context)


    var email: String? = null
        private set
    var password: String? = null
        private set
    var token: String? = null
        private set

    var enablePush: Boolean = false
        private set


    init {
        email = preferenceHelper.email()
        password = preferenceHelper.password()
        token = preferenceHelper.token()
        enablePush = preferenceHelper.enablePushToken()
    }


    fun logout() {
        token = null
        preferenceHelper.saveToken(null)
    }


    fun saveEmail(email: String) {
        this.email = email
        this.preferenceHelper.saveEmail(email)
    }

    fun savePassword(password: String) {
        this.password = password
        this.preferenceHelper.savePassword(password)
    }

    fun saveToken(token: String) {
        this.token = token
        this.preferenceHelper.saveToken(token)
    }

    fun enablePush(enable: Boolean) {
        this.enablePush = enable
        this.preferenceHelper.enablePushToken(enable)
    }
}