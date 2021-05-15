package com.alexe.base.helper

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.util.*

/**
 * @package: com.alexe.base.helper
 * @author: Alex Lee
 * @date: 2021/5/8 10:28
 * @des: <b>此处描述当前类</b>
 */
object MmkvUtil {

    private var mmkv: MMKV? = null

    fun init(context: Context) {
        MMKV.initialize(context)
        mmkv = MMKV.defaultMMKV()
    }

    fun encode(key: String, value: Any) {
        when (value) {
            is String -> mmkv?.encode(key, value)
            is Float -> mmkv?.encode(key, value)
            is Boolean -> mmkv?.encode(key, value)
            is Int -> mmkv?.encode(key, value)
            is Long -> mmkv?.encode(key, value)
            is Double -> mmkv?.encode(key, value)
            is ByteArray -> mmkv?.encode(key, value)
            is Nothing -> return
        }
    }


    /**
     * 存储序列化数据
     */
    fun <T : Parcelable> encode(key: String, t: T?) {
        t?.apply {
            mmkv?.encode(key, t)
        }
    }

    /**
     * 存储Set列表
     */
    fun encode(key: String, set: Set<String>?) {
        if (set == null) {
            return
        }
        mmkv?.encode(key, set)
    }

    /**
     * 获取Int
     */
    fun decodeInt(key: String): Int {
        return mmkv?.decodeInt(key, 0) ?: 0
    }

    /**
     * 获取Double
     */
    fun decodeDouble(key: String): Double {
        return mmkv?.decodeDouble(key, 0.00) ?: 0.00
    }

    /**
     * 获取Long
     */
    fun decodeLong(key: String): Long {
        return mmkv?.decodeLong(key, 0L) ?: 0L
    }

    /**
     * 获取Float
     */
    fun decodeFloat(key: String): Float {
        return mmkv?.decodeFloat(key, 0F) ?: 0F
    }

    /**
     * 获取Boolean
     */
    fun decodeBoolean(key: String): Boolean {
        return mmkv?.decodeBool(key, false) ?: false
    }

    /**
     * 获取String
     */
    fun decodeString(key: String): String {
        return mmkv?.decodeString(key, "") ?: ""
    }

    /**
     * 获取ByteArray
     */
    fun decodeByteArray(key: String): ByteArray {
        return mmkv?.decodeBytes(key) ?: byteArrayOf()
    }

    /**
     * 获取序列化对象
     */
    fun <T : Parcelable> decodeParcelable(key: String, tClass: Class<T>): T? {
        return mmkv?.decodeParcelable(key, tClass)
    }


    /**
     * 获取Set
     */
    fun decodeStringSet(key: String): Set<String> {
        return mmkv?.decodeStringSet(key, Collections.emptySet()) ?: setOf()
    }


    /**
     * 删除一条数据
     */
    fun removeKey(key: String) {
        mmkv?.removeValueForKey(key)
    }

    /**
     * 清空所有数据
     */
    fun clearAll() {
        mmkv?.clearAll()
    }

}