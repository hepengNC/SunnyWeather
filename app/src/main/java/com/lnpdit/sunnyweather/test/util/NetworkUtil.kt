package com.lnpdit.sunnyweather.test.util

import com.blankj.utilcode.util.NetworkUtils
import com.lnpdit.sunnyweather.R

/**
 * Created by HePeng on 2023/7/13
 */
object NetworkUtil {
    fun show() {
        if (NetworkUtils.isConnected()) {
            R.string.unable_to_connect_server_try_again_later.show()
        } else {
            R.string.network_not_connected.show()
        }
    }
}