package com.alexe.www.http

import com.alexe.www.entity.DeviceEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/4/8 14:26
 * @des: <b>此处描述当前类</b>
 */
interface ApiService {

    @GET("/api/biz/device/terminals/mac")
    suspend fun getTokent(
            @Header("System") system: String,
            @Query("mac") serialId: String
    ): IBaseResponse<String>


    @GET("/api/biz/device/terminals/info")
    suspend fun getDeviceInfo(
            @Header("System") system: String,
            @Header("Authorization") auth: String
    ): IBaseResponse<DeviceEntity>

}