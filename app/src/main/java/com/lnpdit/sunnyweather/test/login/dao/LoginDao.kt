package com.lnpdit.sunnyweather.test.login.dao

import android.content.Context
import androidx.core.content.edit
import com.lnpdit.sunnyweather.test.application.MyApplication

/**
 * Created by HePeng on 2023/7/13
 */
object LoginDao {
    fun getSharedPreferences() =
        MyApplication.context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun saveUsername(username: String) {
        getSharedPreferences().edit {
            putString("username", username)
        }
    }

    fun getUsername() = getSharedPreferences().getString("username", "")

    fun saveIsRememberPassword(rememberPassword: Boolean) {
        getSharedPreferences().edit {
            putBoolean("remember_password", rememberPassword)
        }
    }

    fun getIsRememberPassword() = getSharedPreferences().getBoolean("remember_password", false)

    fun savePassword(password: String) {
        getSharedPreferences().edit {
            putString("password", password)
        }
    }

    fun getPassword() = getSharedPreferences().getString("password", "")
    fun saveIsAutoLogin(autoLogin: Boolean) {
        getSharedPreferences().edit {
            putBoolean("autoLogin", autoLogin)
        }
    }

    fun getIsAutoLogin() = getSharedPreferences().getBoolean("autoLogin", false)
    fun saveToken(token: String) {
        getSharedPreferences().edit {
            putString("token", token)
        }
    }

    fun saveManagerId(managerId: String) {
        getSharedPreferences().edit {
            putString("managerId", managerId)
        }
    }
}