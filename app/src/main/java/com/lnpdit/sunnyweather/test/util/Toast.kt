package com.lnpdit.sunnyweather.test.util

import android.widget.Toast
import com.lnpdit.sunnyweather.test.application.MyApplication

/**
 * Created by HePeng on 2023/7/13
 */
fun String.show(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun Int.show(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}