package com.example.productschedule.bean

import com.bin.david.form.annotation.SmartColumn

/**
 * Created by xiao on 2021/6/15
 */
data class TeamTime(
    @SmartColumn(id = 2, name = "班次")
    private val teamTime: String,

//    @SmartColumn(name = "产品信息")
//    private val proMessage: String,
//
//    @SmartColumn(name = "")
//    private val proSpeed: Int,
//
//    @SmartColumn(name = "克重")
//    private val proWeight: String,

)
