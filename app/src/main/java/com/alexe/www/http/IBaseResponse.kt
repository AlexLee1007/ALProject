package com.alexe.www.http

import java.io.Serializable

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/11 11:21
 * @des: <b>此处描述当前类</b>
 */
data class IBaseResponse<T>(
    var code: Int?,
    var data: T?,
    var message: String?,
    var traceId: String?
) : Serializable