package com.alexe.www.http

import com.alexe.base.helper.ALog
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineExceptionHandler
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/11 14:03
 * @des: <b>扩展函数</b>
 */

/**
 * 用于处理仓库中网络请求的异常
 */
fun Any.call(job: (code: Int, message: String) -> Unit): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, throwable ->
        ALog.e(throwable)
        when (throwable) {
            is HttpException -> {
                var errorBody = throwable.response()?.errorBody()?.string()
                errorBody = when (throwable.code()) {
                    404 -> "The right resources were not found"
                    500 -> "Server internal error"
                    else -> "${NetException.BAD_NETWORK} : ${errorBody}"
                }
                job.invoke(throwable.code(), errorBody)
            }
            is SocketTimeoutException -> job.invoke(-1, NetException.CONNECT_ERROR)
            is ConnectException, is UnknownHostException -> job.invoke(-1, NetException.CONNECT_TIMEOUT)
            is JsonParseException, is JSONException, is ParseException -> job.invoke(-1, NetException.PARSE_ERROR)
            else -> job.invoke(-1, NetException.UNKNOWN_ERROR)
        }
    }
}
