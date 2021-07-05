package com.example.productschedule.utils

import android.content.Context
import android.util.Log
import com.example.productschedule.exception.NullBodyException
import retrofit2.Response

object RequestUtil {
    suspend fun <T> request(context: Unit, request: ()-> Response<T>) : T? {
        var result: Response<T>? = null
        try {
            result = request()
            Log.i("request", result.toString())
            if (result.isSuccessful) {
                return result.body() ?: throw NullBodyException()
            } else {
                ExceptionUtil.catchHttpException(result.code())
                return null
            }
        } catch (e:Exception) {
            ExceptionUtil.catchException(e)
            return null
        }
    }

}