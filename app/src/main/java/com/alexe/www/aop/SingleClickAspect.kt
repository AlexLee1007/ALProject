package com.alexe.www.aop

import android.view.View
import com.alexe.base.helper.ALog
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.util.*

/**
 * @package: com.alexe.www.aop
 * @author: Alex Lee
 * @date: 2021/6/1 10:22
 * @des: <b>此处描述当前类</b>
 */
@Aspect
class SingleClickAspect {

    /**
     * 最近一次点击的时间
     */
    private var mLastTime: Long = 0L

    /**
     * 最近一次点击的控件ID
     */
    private var mLastId: Int = 0


    @Pointcut("execution(@com.alexe.www.aop.SingleClick * *(..))")
    fun method() {
    }

    @Around("method() && @annotation(singleClick)")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint, singleClick: SingleClick) {
        var view: View? = null
        for (arg in joinPoint.args) {
            if (arg is View) {
                view = arg
            }
        }
        view?.let {
            var curTime = Calendar.getInstance().timeInMillis
            if (curTime - mLastTime < singleClick.value && it.id == mLastId) {
                ALog.i("触发多次点击")
                return
            }
            mLastTime = curTime
            mLastId = it.id
        }

        joinPoint.proceed()
    }

}