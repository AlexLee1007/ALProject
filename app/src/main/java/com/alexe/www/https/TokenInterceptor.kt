package com.alexe.www.https

import android.content.Intent
import android.text.TextUtils
import com.alexe.base.helper.ALog
import com.alexe.base.helper.AppManager
import com.alexe.base.helper.MmkvUtil
import com.alexe.www.App
import com.alexe.www.BuildConfig
import com.alexe.www.ui.login.LoginActivity
import okhttp3.*
import org.json.JSONObject
import java.lang.IllegalArgumentException

/**
 * @package: com.alexe.www.https
 * @author: Alex Lee
 * @date: 2021/6/9 11:10
 * @des: <b>此处描述当前类</b>
 */
class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)

        val mediaType: MediaType? = request.body?.contentType()
        val body: String? = response.body?.string()

        if (isTokenExpired(body)) {
            val newToken = getNewToken()
            MmkvUtil.encode("refreshToken", newToken)
            if (TextUtils.isEmpty(newToken)) {
                ALog.d("Token Expired : Jump Login...")
                //#TODO 使用拦截器时这里需要设置跳转登录页
                onJumpLoginView()
            } else {
                ALog.d("Token Expired : Refresh Token...")
                val newRequest: Request = chain.request()
                        .newBuilder()
                        .header("Authorization", newToken)
                        .build()
                response.close()
                return chain.proceed(newRequest)
            }
        }
        body?.let {
            return response.newBuilder().body(ResponseBody.create(mediaType, it)).build()
        }
        throw IllegalArgumentException("------> Response Body is NULL")
    }

    private fun onJumpLoginView() {
        val intent = Intent(App.context, LoginActivity::class.java)
        AppManager.startActivity(intent)
        AppManager.finishOtherActivites(LoginActivity::class.java)
    }

    /**
     * 判断Token是否失效
     * 根据接口要求修改
     *
     * @param body
     */
    private fun isTokenExpired(body: String?): Boolean {
        body?.let {
            val job = JSONObject(it)
            return job.optInt("code") == 401
        }
        return false
    }


    /**
     * 重新获取Token
     * 根据接口要求修改,使用同步请求
     *
     */
    private fun getNewToken(): String {
        return ""
        //   return "iaZvMs74x\\/r19FwhbEuJaTBppiWVmRAxoV0zUGjDEiFzHxtWE09RTFdOjM4ci0xzt0pqcT+nIWBKLzGL39iSXyR1TwAglxSAj7\\/f6RvNun9lKBgOnPMkeklA4km6j3iaRJh+NScuqMZQKhdNFaTt\\/5RK2R63BRaiBPz++r9LlzI="
    }

}