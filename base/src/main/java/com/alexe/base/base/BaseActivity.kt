package com.alexe.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alexe.base.helper.ALog
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/**
 * @package: com.alexe.base.base
 * @author: Alex Lee
 * @date: 2021/3/31 11:51
 * @des: <b>此处描述当前类</b>
 */
abstract class BaseActivity<SV : ViewBinding> : AppCompatActivity() {

    protected lateinit var mViewBinding: SV

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initViewBinding())

        initLayout(savedInstanceState)
    }


    private fun initViewBinding(): View {
        val superclass = javaClass.genericSuperclass
        val aClass = (superclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        try {
            val method: Method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
            mViewBinding = method.invoke(null, layoutInflater) as SV
        } catch (e: IllegalAccessException) {
            ALog.e(e)
        } catch (e: InvocationTargetException) {
            ALog.e(e)
        } catch (e: NoSuchMethodException) {
            ALog.e(e)
        }
        return mViewBinding.root
    }


    protected abstract fun initLayout(savedInstanceState: Bundle?)

}