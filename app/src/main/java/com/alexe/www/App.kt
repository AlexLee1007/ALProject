package com.alexe.www

import android.app.Application
import com.alexe.base.helper.ALog
import com.alexe.base.helper.AppManager
import com.alexe.base.helper.HttpHelper
import com.alexe.base.helper.MmkvUtil

/**
 * @author Rick Li
 * @Description <b>此处描述当前类</b>
 * @date 2021/3/30 11:39
 * @Copyright © 2020-2099 西安林杰电子信息科技有限公司  研发项目组
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        initialize()
    }

    private fun initialize() {
        AppManager.init(context)
        MmkvUtil.init(context)
        HttpHelper.init(context)
        ALog.init(BuildConfig.DEBUG, "DOGE")
    }

    companion object {
        lateinit var context: App
    }

}