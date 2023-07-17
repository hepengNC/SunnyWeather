package com.lnpdit.sunnyweather.test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lnpdit.sunnyweather.test.park.dao.ParkDao
import com.lnpdit.sunnyweather.test.park.model.Park
import com.lnpdit.sunnyweather.test.park.model.ParkResponse

/**
 * Created by HePeng on 2023/7/14
 */
@Database(version = 1, entities = [ParkResponse.Park::class])
abstract class MyDatabase : RoomDatabase() {
    abstract fun getParkDao(): ParkDao

    companion object {
        private var instance: MyDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): MyDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "test_database"
            ).build()
                .apply {
                    instance = this
                }
        }
    }
}