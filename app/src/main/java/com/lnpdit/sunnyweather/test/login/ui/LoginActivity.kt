package com.lnpdit.sunnyweather.test.login.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.lnpdit.sunnyweather.R
import com.lnpdit.sunnyweather.databinding.ActivityLoginBinding
import com.lnpdit.sunnyweather.test.base.BaseActivity
import com.lnpdit.sunnyweather.test.login.model.LoginRequest
import com.lnpdit.sunnyweather.test.park.ui.ParkActivity
import com.lnpdit.sunnyweather.test.util.NetworkUtil
import com.lnpdit.sunnyweather.test.util.show
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView

/**
 * Created by HePeng on 2023/7/13
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }
    private lateinit var popupView: BasePopupView
    private lateinit var username: String
    private lateinit var password: String
    private var isRememberPassword: Boolean = false
    private var isAutoLogin: Boolean = false
    private lateinit var token: String
    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun init() {
        if (viewModel.getIsAutoLogin()) {
            startActivity(Intent(this, ParkActivity::class.java))
            finish()
            return
        }
        binding.etUsername.setText(viewModel.getUsername())
        isRememberPassword = viewModel.getIsRememberPassword()
        binding.cbRememberPassword.isChecked = isRememberPassword
        if (isRememberPassword) {
            binding.etPassword.setText(viewModel.getPassword())
        }
        popupView = XPopup.Builder(this)
            .dismissOnTouchOutside(false)
            .asLoading("正在登录...");
        binding.btnLogin.setOnClickListener {
            login()
        }
        viewModel.loginResponseLiveData.observe(this) { result ->
            val response = result.getOrNull()
            if (response != null) {
                val code = response.code
                if (code == 200) {
                    token = response.token
                    viewModel.getInfo(token)
                } else {
                    R.string.login_failed.show()
                    popupView.dismiss()
                }
            } else {
                NetworkUtil.show()
                popupView.dismiss()
            }
        }
        viewModel.getInfoResponseLiveData.observe(this) { result ->
            val response = result.getOrNull()
            if (response != null) {
                val code = response.code
                if (code == 200) {
                    viewModel.saveUsername(username)
                    viewModel.saveIsRememberPassword(isRememberPassword)
                    if (isRememberPassword) {
                        viewModel.savePassword(password)
                    } else {
                        viewModel.savePassword("")
                    }
                    viewModel.saveIsAutoLogin(isAutoLogin)
                    viewModel.saveToken(token)
                    viewModel.saveManagerId(response.user.userId.toString())
                    R.string.login_success.show()
                    val intent = Intent(this, ParkActivity::class.java).apply {
                        putExtra("managerId", response.user.userId.toString())
                    }
                    startActivity(intent)
                    finish()
                } else {
                    R.string.login_failed.show()
                }
            } else {
                NetworkUtil.show()
            }
            popupView.dismiss()
        }
    }

    private fun login() {
        username = binding.etUsername.text.toString().trim()
        if (username.isEmpty()) {
            R.string.login_hint_username.show()
            return
        }
        password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()) {
            R.string.login_hint_password.show()
            return
        }
        isRememberPassword = binding.cbRememberPassword.isChecked
        isAutoLogin = binding.cbAutoLogin.isChecked
        popupView.show()
        viewModel.login(LoginRequest(username, password))
    }
}