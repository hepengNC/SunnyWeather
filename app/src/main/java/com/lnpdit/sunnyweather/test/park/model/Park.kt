package com.lnpdit.sunnyweather.test.park.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by HePeng on 2023/7/14
 */
@Entity
data class Park(
    val createBy: String,
    val createTime: String,
    val updateBy: String,
    val updateTime: String,
    val remark: String,
    val parkId: Int,
    val number: String,
    val name: String,
    val description: String,
    val parkType: String,
    val baseId: Int,
    val baseName: String,
    val baseNumber: String,
    val managerId: Int,
    val managerName: String,
    val provinceId: Int,
    val province: String,
    val cityId: Int,
    val city: String,
    val areaId: Int,
    val area: String,
    val streetId: Int,
    val street: String,
    val lng: Double,
    val lat: Double,
    val parkArea: Double,
    val equipmentQuantity: Int,
    val plotQuantity: Int,
    val employeesQuantity: Int,
    val machineQuantity: Int,
    val delFlag: String,
    val ratio: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}