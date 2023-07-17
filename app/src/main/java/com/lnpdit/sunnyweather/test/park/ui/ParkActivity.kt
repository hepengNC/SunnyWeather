package com.lnpdit.sunnyweather.test.park.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lnpdit.sunnyweather.databinding.ActivityParkBinding
import com.lnpdit.sunnyweather.test.application.MyApplication
import com.lnpdit.sunnyweather.test.base.BaseActivity
import com.lnpdit.sunnyweather.test.database.MyDatabase
import com.lnpdit.sunnyweather.test.login.ui.LoginActivity
import com.lnpdit.sunnyweather.test.login.ui.LoginViewModel
import com.lnpdit.sunnyweather.test.park.model.Park
import com.lnpdit.sunnyweather.test.park.model.ParkRequest
import com.lnpdit.sunnyweather.test.park.model.ParkResponse
import com.lnpdit.sunnyweather.test.util.NetworkUtil
import com.lnpdit.sunnyweather.test.util.show
import kotlin.concurrent.thread

/**
 * Created by HePeng on 2023/7/13
 */
class ParkActivity : BaseActivity<ActivityParkBinding>() {
    val viewModel by lazy { ViewModelProvider(this).get(ParkViewModel::class.java) }
    val loginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }
    private lateinit var adapter: ParkAdapter
    private val parkList = ArrayList<ParkResponse.Park>()
    override fun getViewBinding(): ActivityParkBinding {
        return ActivityParkBinding.inflate(layoutInflater)
    }

    override fun init() {
        val parkDao = MyDatabase.getDatabase(this).getParkDao()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        thread {
            val list = viewModel.loadAllParks(parkDao)
            runOnUiThread {
                parkList.clear()
                parkList.addAll(list)
                adapter = ParkAdapter(parkList)
                binding.recyclerView.adapter = adapter

                viewModel.getParkList(
                    ParkRequest(
                        MyApplication.instance.getToken().toString(),
                        MyApplication.instance.getManagerId().toString()
                    )
                )
            }
        }
        binding.btnExitLogin.setOnClickListener {
            loginViewModel.saveIsAutoLogin(false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        viewModel.responseLiveData.observe(this) { result ->
            val response = result.getOrNull()
            if (response != null) {
                val code = response.code
                if (code == 200) {
                    thread {
                        viewModel.deleteAllParks(parkDao)
                        for (park in response.rows) {
                            viewModel.insertPark(parkDao,park)
                        }
                        runOnUiThread {
                            parkList.clear()
                            val list = response.rows
                            parkList.addAll(list)
                            adapter.notifyDataSetChanged()
                        }
                    }
                } else {
                    response.msg.show()
                }
            } else {
                NetworkUtil.show()
            }
        }
    }
}