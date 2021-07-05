package com.example.productschedule.utils

import android.accounts.NetworkErrorException
import android.content.res.Resources
import com.example.productschedule.BaseApplication
import com.example.productschedule.R
import com.example.productschedule.exception.NullBodyException
import com.example.productschedule.utils.ToastUtils.toast
import com.google.gson.stream.MalformedJsonException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionUtil {

    /**
     * 处理异常
     */
    suspend fun catchException(e: Exception) {
        e.printStackTrace()
        val msg = when (e) {
            is HttpException -> {
                catchHttpException(e.code())
                return
            }
            is SocketTimeoutException -> R.string.common_error_net_time_out
            is UnknownHostException, is NetworkErrorException, is ConnectException -> R.string.common_error_net
            is NullPointerException, is ClassCastException, is Resources.NotFoundException,is MalformedJsonException -> R.string.common_error_do_something_fail
            is NullBodyException -> R.string.common_error_server_body_null
            else -> R.string.common_error_do_something_fail
        }
        withContext(Dispatchers.Main) {
            BaseApplication.getContext().resources.getText(msg).toast()
        }
    }

    /**
     * 处理网络异常
     */
    suspend fun catchHttpException(errorCode: Int) {
        if (errorCode in 200 until 300) return// 成功code则不处理
        showToast(catchHttpExceptionCode(errorCode), errorCode)
    }

    /**
     * toast提示
     */
    private suspend fun showToast(errorMsg: Int, errorCode: Int) {
        withContext(Dispatchers.Main) {
            with(BaseApplication.getContext()) {
                "${this.resources.getText(errorMsg)}：$errorCode".toast()
            }
        }
    }

    private fun catchHttpExceptionCode(errorCode: Int): Int = when (errorCode) {
        in 500..600 -> R.string.common_error_server
        in 400 until 500 -> R.string.common_error_request
        else -> R.string.common_error_request
    }


}