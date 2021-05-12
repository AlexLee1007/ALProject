package com.alexe.www.http

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/11 14:01
 * @des: <b>此处描述当前类</b>
 */
class ApiResponse<T> {

    var code: Int? = SUCCESS_CODE
    var body: T? = null
    var msg: String? = null

    constructor(responese: Response<T>) {
        body = responese.body()
    }

    constructor(response: IBaseResponse<T>) {
        code = response.code
        body = response.data
        msg = response.message
    }

    constructor(t: T) {
        body = t
    }

    constructor(throwable: Throwable) {
        throwable.printStackTrace()
        body = null
        code = ERROR_CODE
        msg = when (throwable) {
            is HttpException -> {
                code = throwable.code()
                val errorBody = throwable.response()?.errorBody()?.string()
                when (throwable.code()) {
                    404 -> "The right resources were not found"
                    500 -> "Server internal error"
                    else -> "${NetException.BAD_NETWORK} : ${errorBody}"
                }
            }
            is SocketTimeoutException -> NetException.CONNECT_TIMEOUT
            is ConnectException, is UnknownHostException -> NetException.CONNECT_ERROR
            is JsonParseException, is JSONException, is ParseException -> NetException.PARSE_ERROR
            else -> throwable.message
        }
    }

    companion object {
        const val ERROR_CODE = 999
        const val SUCCESS_CODE = 200
    }

}