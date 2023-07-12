package com.lnpdit.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnpdit.sunnyweather.logic.Repository
import com.lnpdit.sunnyweather.logic.dao.PlaceDao
import com.lnpdit.sunnyweather.logic.model.PlaceResponse

/**
 * Created by HePeng on 2023/7/11
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<PlaceResponse.Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: PlaceResponse.Place) = PlaceDao.savePlace(place)
    fun getPlace() = PlaceDao.getPlace()
    fun isSavePlace() = PlaceDao.isSavePlace()
}