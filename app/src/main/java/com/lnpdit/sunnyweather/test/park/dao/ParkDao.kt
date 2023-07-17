package com.lnpdit.sunnyweather.test.park.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lnpdit.sunnyweather.test.park.model.ParkResponse

/**
 * Created by HePeng on 2023/7/14
 */
@Dao
interface ParkDao {
    @Insert
    fun insertPark(park: ParkResponse.Park): Long

    @Update
    fun updatePark(newPark: ParkResponse.Park)

    @Query("select * from Park")
    fun loadAllParks(): List<ParkResponse.Park>

    @Query("delete from Park")
    fun deleteAllParks()
}