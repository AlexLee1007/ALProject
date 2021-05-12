package com.alexe.base.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*

/**
 * @package: com.alexe.base.helper
 * @author: Alex Lee
 * @date: 2021/5/7 16:43
 * @des: <b>此处描述当前类</b>
 */
object AppManager : Application.ActivityLifecycleCallbacks {

    private val activityStack = Stack<Activity>()

    var application: Application? = null
        private set

    fun init(application: Application) {
        this.application = application
        application.registerActivityLifecycleCallbacks(this)
    }

    /**
     *  获取栈顶的Activity
     */
    val topActivity: Activity
        get() = activityStack.lastElement()


    /**
     *  清空画面栈，退出程序
     */
    fun finishAllActivity() {
        while (true) {
            if (activityStack.isEmpty()) {
                break
            }
            topActivity.finish()
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityStack.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        activityStack.remove(activity)
    }

}