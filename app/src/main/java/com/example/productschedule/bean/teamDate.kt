package com.example.productschedule.bean

import com.bin.david.form.annotation.ColumnType
import com.bin.david.form.annotation.SmartColumn

/**
 * Created by xiao on 2021.6.15
 */
data class TeamDate(
    @SmartColumn(id = 1, name = "日期")
    private val teamDate: String,
    @SmartColumn(type = ColumnType.ArrayChild)
    private val teamTimes: List<TeamTime>,

    )
