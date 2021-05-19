package com.alexe.www.http

import com.alexe.www.App
import com.alexe.www.R

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/11 15:09
 * @des: <b>此处描述当前类</b>
 */
object NetException {

    /**
     * 网络连接失败,请检查网络
     */
    @JvmField
    val CONNECT_ERROR: String = App.context.getString(R.string.connect_error)

    /**
     * 连接超时,请稍后再试
     */
    @JvmField
    val CONNECT_TIMEOUT: String = App.context.getString(R.string.connect_timeout)

    /**
     * 解析服务器响应数据失败
     */
    @JvmField
    val PARSE_ERROR: String = App.context.getString(R.string.parse_error)

    /**
     * 服务器异常
     */
    @JvmField
    val BAD_NETWORK: String = App.context.getString(R.string.server_error)

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR: String = App.context.getString(R.string.unknown_error)

    /**
     * 服务器返回数据失败
     */
    @JvmField
    val RESPONSE_ERROR: String = App.context.getString(R.string.response_error)

}