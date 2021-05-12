package com.alexe.www.constant

/**
 * @package: com.alexe.www.constant
 * @author: Alex Lee
 * @date: 2021/4/8 11:56
 * @des: <b>此处描述当前类</b>
 */
object Constant {

    /**
     * 数据库名称
     */
    const val DB_NAME = "yun_database.db"

    /**
     * 网络请求地址
     */
    const val HTTP_REQUET_IP = "http://ldkfapi.ldaqy.cn/"

    /**
     * 请求超时时间
     */
    const val REQUEST_NETWORK_TIME: Long = 1000 * 15 * 1

    /**
     * 读取超时时间
     */
    const val READ_NETWORK_TIME: Long = 1000 * 90 * 1

    /**
     * 写入超时时间
     */
    const val WRITE_NETWORK_TIME: Long = 1000 * 90 * 1;

}