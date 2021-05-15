package com.alexe.base.helper

/**
 * @package: com.alexe.base.helper
 * @author: Alex Lee
 * @date: 2021/5/14 15:45
 * @des: <b>防止按钮多次点击</b>
 */
object ClickHelper {

    private var lastClickTime: Long = 0

    private var TIME_INTERVAL = 1000L

    fun isFastClick(): Boolean {
        var bool = false
        val startTime = System.currentTimeMillis()
        val endTime = startTime - lastClickTime

        if (endTime > TIME_INTERVAL) {
            bool = true
        }
        lastClickTime = startTime
        return bool
    }

}