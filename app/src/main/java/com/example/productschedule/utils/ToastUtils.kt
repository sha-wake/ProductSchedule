package com.example.productschedule.utils

import android.view.Gravity
import android.widget.Toast
import com.example.productschedule.BaseApplication

object ToastUtils {
    private val t: Toast by lazy { Toast.makeText(BaseApplication.getContext(), "", Toast.LENGTH_SHORT) }
    fun Any.toast(duration: Int = Toast.LENGTH_SHORT): Toast {
        val str = this
        return t.apply {setText(str.toString());setDuration(duration);setGravity(Gravity.TOP, 0, 70);show() }
    }
}