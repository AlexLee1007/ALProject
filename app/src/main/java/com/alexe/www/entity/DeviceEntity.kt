package com.alexe.www.entity

/**
 * @package: com.alexe.www.entity
 * @author: Alex Lee
 * @date: 2021/6/9 17:06
 * @des: <b>此处描述当前类</b>
 */

data class DeviceEntity(
    val address: String,
    val category: Any,
    val classesId: Any,
    val clientId: Int,
    val clientName: Any,
    val code: String,
    val description: Any,
    val deviceId: Int,
    val deviceStatus: Int,
    val endDate: String,
    val gridId: Int,
    val id: Int,
    val ip: String,
    val mac: String,
    val name: String,
    val oauthCode: String,
    val oauthType: Int,
    val permanent: Boolean,
    val startDate: String,
    val type: Int
)