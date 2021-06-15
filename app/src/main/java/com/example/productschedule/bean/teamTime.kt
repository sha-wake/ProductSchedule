package com.example.productschedule.bean

import com.bin.david.form.annotation.ColumnType
import com.bin.david.form.annotation.SmartColumn

data class TeamTime(
    @SmartColumn(id = 2, name = "班次", type = ColumnType.ArrayOwn)
    private val teamTime: String
)
