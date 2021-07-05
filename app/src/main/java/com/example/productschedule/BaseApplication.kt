package com.example.productschedule

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import com.example.productschedule.utils.SharePreUtils
import com.example.productschedule.bean.IpBean
import com.google.gson.Gson
import data.BaseConstant

import java.util.*

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        loadLanguage()
        if (SharePreUtils.setinfo.getString(BaseConstant.CUR_IP, "") == "") {
            SharePreUtils.setinfo.edit().putString(BaseConstant.CUR_IP, Gson().toJson(IpBean("192.168.11.243", "PDAServer"))).apply()
        }
    }

    private fun loadLanguage() {
        val locale: Locale
        locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales[0]
        } else {
            resources.configuration.locale
        }
        val slang = locale.language + '-' + locale.country
        var lang = "0"
        if (slang.contains("HK") || slang.contains("Macau") || slang.contains("tw")) {
            lang = "1"
        } else if (slang.contains("en")) {
            lang = "2"
        } else if (slang.contains("th")) {
            lang = "3"
        } else if (slang.contains("zh")) {
            lang = "0"
        }
        SharePreUtils.setinfo.edit().putString(BaseConstant.LANG, lang).apply()
        Log.i("语言", slang)
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        fun getContext(): Context {
            return context
        }
    }
}