package com.lnpdit.sunnyweather.test.park.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnpdit.sunnyweather.test.application.MyApplication
import com.lnpdit.sunnyweather.test.database.MyDatabase
import com.lnpdit.sunnyweather.test.http.MyRepository
import com.lnpdit.sunnyweather.test.park.dao.ParkDao
import com.lnpdit.sunnyweather.test.park.model.Park
import com.lnpdit.sunnyweather.test.park.model.ParkRequest
import com.lnpdit.sunnyweather.test.park.model.ParkResponse

/**
 * Created by HePeng on 2023/7/13
 */
class ParkViewModel : ViewModel() {
    private val requestLiveData = MutableLiveData<ParkRequest>()
    val responseLiveData = Transformations.switchMap(requestLiveData) { request ->
        MyRepository.getParkList(request.token, request.managerId)
    }

    fun getParkList(request: ParkRequest) {
        requestLiveData.value = request
    }

    fun insertPark(parkDao: ParkDao, park: ParkResponse.Park) {
        park.id = parkDao.insertPark(park)
    }

    fun updatePark(parkDao: ParkDao,newPark: ParkResponse.Park) {
        parkDao.updatePark(newPark)
    }

    fun loadAllParks(parkDao: ParkDao) = parkDao.loadAllParks()
    fun deleteAllParks(parkDao: ParkDao) {
        parkDao.deleteAllParks()
    }
}