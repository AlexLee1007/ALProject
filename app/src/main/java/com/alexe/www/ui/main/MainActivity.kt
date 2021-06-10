package com.alexe.www.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alexe.base.base.BaseActivity
import com.alexe.base.helper.ALog
import com.alexe.base.helper.HttpHelper
import com.alexe.www.R
import com.alexe.www.aop.SingleClick
import com.alexe.www.databinding.ActivityMainBinding
import com.alexe.www.http.HttpResult
import com.alexe.www.ui.InjectorUtil
import java.lang.Exception


class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {

    val viewModel by lazy {
        ViewModelProvider(this, InjectorUtil.getMainFactory()).get(MainViewModel::class.java)
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        mViewBinding.updateBtn.setOnClickListener(this)
        mViewBinding.infoBtn.setOnClickListener(this)
        viewModel.text.observe(this, Observer {
            when (it) {
                is HttpResult.Success -> {
                    it?.let {
                        mViewBinding.mainText.text = " text : ${it.data}"
                    }
                }
                is HttpResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "这是一个加载弹框", Toast.LENGTH_SHORT).show()
                }
            }
        })

        ALog.i("${HttpHelper.NETWORK_ENABLE}")
        ALog.i("${HttpHelper.WIFI_AVAILABLE}")
        ALog.i("${HttpHelper.MAC_ADDRESS}")

        try {
            throw NullPointerException("");
        } catch (e: Exception) {
            ALog.e(e)
        }

    }

    @SingleClick
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.updateBtn -> viewModel.getTokent("s")
            R.id.infoBtn -> viewModel.getDeviceInfo()
            else -> {
            }
        }
    }

    companion object {

        private const val TAG = "MainActivity"

    }

}