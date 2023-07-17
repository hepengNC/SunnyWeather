package com.lnpdit.sunnyweather.test.http

import androidx.lifecycle.liveData
import com.lnpdit.sunnyweather.test.login.model.LoginRequest
import com.lnpdit.sunnyweather.test.login.network.LoginNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by HePeng on 2023/7/13
 */
object MyRepository {
    fun login(request: LoginRequest) = fire(Dispatchers.IO) {
        val loginResponse = LoginNetwork.login(request)
        Result.success(loginResponse)
    }

    fun getInfo(token: String) = fire(Dispatchers.IO) {
        val loginInfoResponse = LoginNetwork.getInfo(token)
        Result.success(loginInfoResponse)
    }

    fun getParkList(token: String, managerId: String) = fire(Dispatchers.IO) {
        val response = LoginNetwork.getParkList(token, managerId)
        Result.success(response)
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}