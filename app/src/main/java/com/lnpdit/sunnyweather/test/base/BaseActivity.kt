package com.lnpdit.sunnyweather.test.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by HePeng on 2023/7/13
 */
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    abstract fun getViewBinding(): T
    abstract fun init()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        init()
    }
}