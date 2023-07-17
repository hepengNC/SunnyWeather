package com.lnpdit.sunnyweather.test.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnpdit.sunnyweather.test.http.MyRepository
import com.lnpdit.sunnyweather.test.login.dao.LoginDao
import com.lnpdit.sunnyweather.test.login.model.LoginRequest

/**
 * Created by HePeng on 2023/7/13
 */
class LoginViewModel : ViewModel() {
    private val loginRequestLiveData = MutableLiveData<LoginRequest>()
    private val getInfoRequestLiveData = MutableLiveData<String>()
    val loginResponseLiveData = Transformations.switchMap(loginRequestLiveData) { request ->
        MyRepository.login(request)
    }

    val getInfoResponseLiveData = Transformations.switchMap(getInfoRequestLiveData) { token ->
        MyRepository.getInfo(token)
    }

    fun login(request: LoginRequest) {
        loginRequestLiveData.value = request
    }

    fun getInfo(token: String) {
        getInfoRequestLiveData.value = token
    }

    fun saveUsername(username: String) = LoginDao.saveUsername(username)
    fun getUsername() = LoginDao.getUsername()
    fun saveIsRememberPassword(rememberPassword: Boolean) =
        LoginDao.saveIsRememberPassword(rememberPassword)

    fun getIsRememberPassword() = LoginDao.getIsRememberPassword()
    fun savePassword(password: String) = LoginDao.savePassword(password)
    fun getPassword() = LoginDao.getPassword()
    fun saveIsAutoLogin(autoLogin: Boolean) = LoginDao.saveIsAutoLogin(autoLogin)
    fun getIsAutoLogin() = LoginDao.getIsAutoLogin()
    fun saveToken(token: String) = LoginDao.saveToken(token)
    fun saveManagerId(managerId: String) = LoginDao.saveManagerId(managerId)
}