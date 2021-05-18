package com.alexe.base.helper

import android.nfc.Tag
import android.util.Log
import com.orhanobut.logger.*


/**
 * @package: com.alexe.base.helper
 * @author: Alex Lee
 * @date: 2021/5/8 13:43
 * @des: <b>此处描述当前类</b>
 */
object ALog {

    private var isDebug = false

    fun init(debug: Boolean, tag: String) {
        val strategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag(tag).build()
        Logger.addLogAdapter(AndroidLogAdapter(strategy))
        isDebug = debug

    }

    fun i(msg: String) {
        if (isDebug) {
            Logger.i(msg)
        }
    }

    fun v(msg: String) {
        if (isDebug) {
            Logger.v(msg)
        }
    }

    fun e(throwable: Throwable): Boolean {
        if (isDebug) {
            Logger.d(throwable)
        }
        return false
    }

    fun w(msg: String) {
        if (isDebug) {
            Logger.w(msg)
        }
    }

    fun d(msg: String) {
        if (isDebug) {
            Logger.d(msg)
        }
    }

}