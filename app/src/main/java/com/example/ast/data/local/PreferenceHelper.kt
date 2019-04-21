package com.example.ast.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper (context: Context) {


    private val preferences: SharedPreferences

    init {
        this.preferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun saveEmail(email: String) {
        putString("email", email)
    }

    fun email() = preferences.getString("email", null)

    fun savePassword(password: String) {
        putString("password", password)
    }

    fun password() = preferences.getString("password", null)

    fun saveToken(token: String?) {
        putString("token", token)
    }

    fun token() = preferences.getString("token", null)

    fun enablePushToken(enable: Boolean) {
        putBoolean("enable_push", enable)
    }

    fun enablePushToken() = preferences.getBoolean("enable_push", true)


    private fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    private fun putBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    private fun putString(key: String, value: String?) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getInt(key: String): Int {
        return preferences.getInt(key, -1)
    }

    private fun putInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun getLong(key: String): Long {
        return preferences.getLong(key, -1)
    }

    private fun putLong(key: String, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    companion object {
        private val SHARED_PREFERENCE = "com.example.ast.prefer"
    }
}