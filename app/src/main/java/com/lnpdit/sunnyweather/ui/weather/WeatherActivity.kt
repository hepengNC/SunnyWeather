package com.lnpdit.sunnyweather.ui.weather

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.lnpdit.sunnyweather.R
import com.lnpdit.sunnyweather.databinding.ActivityWeatherBinding
import com.lnpdit.sunnyweather.logic.model.Weather
import com.lnpdit.sunnyweather.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HePeng on 2023/7/12
 */
class WeatherActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeatherBinding
    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (viewModel.lng.isEmpty()) {
            viewModel.lng = intent.getStringExtra("lng") ?: ""
        }
        if (viewModel.lat.isEmpty()) {
            viewModel.lat = intent.getStringExtra("lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("placeName") ?: ""
        }
        viewModel.weather.observe(this) { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                refreshWeather(weather)
            } else {
                Toast.makeText(this, "暂无天气数据", Toast.LENGTH_SHORT).show()
            }
            binding.refreshLayout.isRefreshing = false
        }
        binding.refreshLayout.setColorSchemeColors(getColor(R.color.purple_500))
        binding.refreshLayout.setOnRefreshListener {
            refreshWeather()
        }
        refreshWeather()

        binding.now.ivNav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(drawerView.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })
    }

    fun refreshWeather() {
        viewModel.refreshWeather(viewModel.lng, viewModel.lat)
        binding.refreshLayout.isRefreshing = false
    }

    private fun refreshWeather(weather: Weather) {
        binding.now.placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        binding.now.currentTemp.text = realtime.result.realtime.temperature.toString()
        val skycon = realtime.result.realtime.skycon
        binding.now.currentSky.text = getSky(skycon).info
        binding.now.currentAQI.text = realtime.result.realtime.air_quality.aqi.chn.toString()
        binding.now.nowLayout.setBackgroundResource(getSky(skycon).bg)
        binding.forecast.forecastLayout.removeAllViews()
        val days = daily.result.daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.result.daily.skycon[i]
            val view: View = LayoutInflater.from(this).inflate(R.layout.forecast_item, null)
            val dateinfo: TextView = view.findViewById(R.id.dateInfo)
            val skyIcon: ImageView = view.findViewById(R.id.skyIcon)
            val skyInfo: TextView = view.findViewById(R.id.skyInfo)
            val temperatureInfo: TextView = view.findViewById(R.id.temperatureInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateinfo.text = simpleDateFormat.format(simpleDateFormat.parse(skycon.date))
            skyIcon.setImageResource(getSky(skycon.value).icon)
            skyInfo.text = getSky(skycon.value).info
            val mintemp = daily.result.daily.temperature[i].min.toInt()
            val maxtemp = daily.result.daily.temperature[i].max.toInt()
            temperatureInfo.text = "$mintemp~$maxtemp℃"
            binding.forecast.forecastLayout.addView(view)
        }
        val lifeindex = daily.result.daily.life_index
        binding.lifeindex.carWashingText.text = lifeindex.carWashing[0].desc
        binding.lifeindex.coldRiskText.text = lifeindex.coldRisk[0].desc
        binding.lifeindex.dressingText.text = lifeindex.dressing[0].desc
        binding.lifeindex.ultravioletText.text = lifeindex.ultraviolet[0].desc
        binding.weatherLayout.visibility = View.VISIBLE
    }

}