package com.example.productschedule.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.productschedule.BaseApplication

object SharePreUtils {
    val setinfo: SharedPreferences by lazy {
        BaseApplication.getContext()
            .getSharedPreferences("GlobalData", Context.MODE_PRIVATE)

    }
}