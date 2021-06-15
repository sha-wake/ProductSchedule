package com.example.productschedule.bean

import com.bin.david.form.annotation.SmartColumn

data class TeamDate(
    @SmartColumn(id = 1, name = "teamDate")
    private val teamDate: String,
    @SmartColumn(id = 2, name = "teamTimes")
    private val teamTimes: String,

    )
