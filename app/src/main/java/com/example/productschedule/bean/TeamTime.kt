package com.example.productschedule.bean

import com.bin.david.form.annotation.SmartColumn

/**
 * Created by xiao on 2021.6.15
 */
data class TeamTime(
    @SmartColumn(id = 2, name = "班次")
    private val teamTime: String,

)
