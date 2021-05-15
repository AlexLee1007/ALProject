package com.alexe.base.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

/**
 * @package: com.alexe.base.helper
 * @author: Alex Lee
 * @date: 2021/5/15 9:08
 * @des: <b>此处描述当前类</b>
 */
object HttpHelper {

    private var mContext: Context? = null

    /**
     * 当前网络是否可用
     */
    val NETWORK_ENABLE: Boolean
        get() {
            val manager =
                mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network: Network? = manager.activeNetwork
            val nc: NetworkCapabilities? = manager.getNetworkCapabilities(network)

            return nc != null && (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        }

//    val WIFI_AVAILABLE: Boolean
//        get() {
//            val manager =
//                mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//
//        }


    fun init(context: Context) {
        this.mContext = context
    }


}