package com.alexe.www.http

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/10 17:17
 * @des: <b>此处描述当前类</b>
 */
sealed class HttpResult<T>(val data: T? = null, val code: Int? = 0, val message: String? = null) {

    class Success<T>(data: T? = null) : HttpResult<T>(data = data)

    class Error<T>(data: T? = null, code: Int?, message: String?) :
        HttpResult<T>(data = data, code = code, message = message)

    class Loading<T>() : HttpResult<T>()

}