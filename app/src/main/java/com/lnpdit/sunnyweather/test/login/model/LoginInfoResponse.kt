package com.lnpdit.sunnyweather.test.login.model

/**
 * Created by HePeng on 2023/7/13
 */
data class LoginInfoResponse(
    val msg: String,
    val code: Int,
    val permissions: List<String>,
    val roles: List<String>,
    val user: User
) {
    data class User(
        val createBy: String,
        val createTime: String,
        val updateBy: String,
        val updateTime: String,
        val remark: String,
        val userId: Int,
        val deptId: String,
        val userName: String,
        val nickName: String,
        val email: String,
        val phonenumber: String,
        val sex: String,
        val avatar: String,
        val password: String,
        val status: String,
        val delFlag: String,
        val loginIp: String,
        val loginDate: String,
        val dept: String,
        val roles: List<Role>,
        val roleIds: String,
        val postIds: String,
        val roleId: Int,
        val roleName: String,
        val farmPlotName: String,
        val belong: String,
        val farmPlotId: String,
        val farmBaseId: Int,
        val farmParkId: String
    ) {
        data class Role(
            val createBy: String,
            val createTime: String,
            val updateBy: String,
            val updateTime: String,
            val remark: String,
            val roleId: Int,
            val roleName: String,
            val roleKey: String,
            val roleSort: Int,
            val dataScope: String,
            val menuCheckStrictly: Boolean,
            val deptCheckStrictly: Boolean,
            val status: String,
            val delFlag: String,
            val flag: Boolean,
            val menuIds: String,
            val deptIds: String,
            val permissions: String,
            val admin: Boolean
        )
    }
}