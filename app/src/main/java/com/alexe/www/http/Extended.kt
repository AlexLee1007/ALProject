package com.alexe.www.http

import com.alexe.base.base.BaseRepository
import java.lang.Exception

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/11 14:03
 * @des: <b>扩展函数</b>
 */


suspend fun <T> BaseRepository.call(job: suspend () -> IBaseResponse<T>): ApiResponse<T> {
    return try {
        ApiResponse(job())
    } catch (e: Exception) {
        ApiResponse(e)
    }
}