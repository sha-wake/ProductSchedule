package com.example.productschedule.bean

import com.bin.david.form.annotation.ColumnType
import com.bin.david.form.annotation.SmartColumn

/**
 * Created by xiao on 2021/6/15
 */
data class TeamDate(
    @SmartColumn(id = 1, name = "日期")
    private val teamDate: String,

    @SmartColumn(type = ColumnType.ArrayChild)
    private val teamTimes: List<TeamTime>,

    @SmartColumn(name = "产品信息")
    private val proMessage: String,

//    @SmartColumn(name = "产线速度")
//    private val proSpeed: Int,

    @SmartColumn(name = "克重")
    private val proWeight: String,
    )
