package com.lnpdit.sunnyweather.test.login.model

/**
 * Created by HePeng on 2023/7/13
 */
data class LoginResponse(
    val msg: String,
    val code: Int,
    val token: String
)