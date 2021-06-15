package com.example.productschedule.bean

import com.bin.david.form.annotation.SmartColumn

data class UserInfo(
    @SmartColumn(id =1,name = "姓名")
    private val name: String,
    @SmartColumn(id=2,name="年龄")
    private val age: Int
)
