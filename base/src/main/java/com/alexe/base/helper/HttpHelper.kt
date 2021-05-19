package com.alexe.base.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.text.TextUtils
import java.io.IOException
import java.io.InputStreamReader
import java.io.LineNumberReader
import java.lang.StringBuilder
import java.net.NetworkInterface
import java.util.*
import kotlin.collections.ArrayList

/**
 * @package: com.alexe.base.helper
 * @author: Alex Lee
 * @date: 2021/5/15 9:08
 * @des: <b>此处描述当前类</b>
 */
object HttpHelper {

    private var mContext: Context? = null


    fun init(context: Context) {
        this.mContext = context
    }


    /**
     * 当前网络是否可用
     */
    val NETWORK_ENABLE: Boolean
        get() = try {
            val manager =
                mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network: Network? = manager.activeNetwork
            val nc: NetworkCapabilities? = manager.getNetworkCapabilities(network)

            nc != null && (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } catch (e: Exception) {
            ALog.e(e)
            false
        }


    /**
     * 当前 WIFI网络是否可用
     */
    val WIFI_AVAILABLE: Boolean
        get() = try {
            val manager =
                mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network: Network? = manager.activeNetwork
            val nc: NetworkCapabilities? = manager.getNetworkCapabilities(network)

            nc != null && nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } catch (e: Exception) {
            ALog.e(e)
            false
        }


    /**
     * 获取MAC地址
     */
    val MAC_ADDRESS: String
        get() {
            var mac = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                getMacDefault()
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                getMacAddress()
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getMacFromHardware()
            } else {
                ""
            }
            return mac
        }


    /**
     *  Android 6.0 之前（不包括6.0）获取mac地址
     */
    private fun getMacDefault(): String {
        var mac = ""
        try {
            val wifi: WifiManager = mContext!!.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val info = wifi.connectionInfo
            if (info != null) {
                mac = info.macAddress
                if (!TextUtils.isEmpty(mac)) {
                    mac = mac.toUpperCase(Locale.ENGLISH);
                }
            }
        } catch (e: Exception) {
            ALog.e(e)
        }
        return mac
    }

    /**
     * Android 6.0-Android 7.0 获取mac地址
     */
    private fun getMacAddress(): String {
        var macSerial = ""
        var str = ""
        try {
            val pr = Runtime.getRuntime().exec("cat/sys/class/net/wlan0/address")
            val ir = InputStreamReader(pr.inputStream)
            val input = LineNumberReader(ir)

            while (null != str) {
                str = input.readLine()
                if (str != null) {
                    macSerial = str.trim()
                }
            }
        } catch (ex: IOException) {
            ALog.e(ex)
        }
        return macSerial
    }


    /**
     * Android 7.0之后获取Mac地址
     */
    private fun getMacFromHardware(): String {
        var mac = ""
        try {
            val all: ArrayList<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())

            for (nif in all) {
                if (!nif.name.equals("wlan0", ignoreCase = true))
                    continue
                var macBytes: ByteArray = nif.hardwareAddress
                if (macBytes != null) {
                    val strbu = StringBuffer()
                    for (macByte in macBytes) {
                        strbu.append(String.format("%02X", macByte))
                    }
                    mac = strbu.toString()
                }
            }

        } catch (e: Exception) {
            ALog.e(e)
        }
        return mac
    }

}