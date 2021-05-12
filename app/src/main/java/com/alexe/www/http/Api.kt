package com.alexe.www.http

import com.alexe.www.App
import com.alexe.www.BuildConfig
import com.alexe.www.constant.Constant
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.lang.Exception
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/4/8 15:00
 * @des: <b>此处描述当前类</b>
 */
object Api {


    private val mClient: OkHttpClient
        get() = try {
            val cacheFile = File(App.mContext.codeCacheDir, "cache")
            val cache = Cache(cacheFile, 1024 * 1024 * 100)
            val builder = OkHttpClient.Builder()
                .connectTimeout(Constant.REQUEST_NETWORK_TIME, TimeUnit.MILLISECONDS)
                .readTimeout(Constant.READ_NETWORK_TIME, TimeUnit.MILLISECONDS)
                .writeTimeout(Constant.WRITE_NETWORK_TIME, TimeUnit.MILLISECONDS)
                .cache(cache)
                .addInterceptor(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor(HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY)
                    else
                        HttpLoggingInterceptor(HttpLogger()).setLevel(HttpLoggingInterceptor.Level.NONE)
                )
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException()
        }


    private val mRetrofit: Retrofit by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.HTTP_REQUET_IP)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(mClient)
            .build()
        return@lazy retrofit
    }

    inline fun <reified T> create(): T = create(T::class.java)

    fun <T> create(service: Class<T>): T = mRetrofit.create(service)

}