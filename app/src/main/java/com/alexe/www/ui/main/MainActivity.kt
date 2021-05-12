package com.alexe.www.ui.main

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alexe.base.base.BaseActivity
import com.alexe.www.databinding.ActivityMainBinding
import com.alexe.www.http.HttpResult
import com.alexe.www.ui.InjectorUtil


class MainActivity : BaseActivity<ActivityMainBinding>() {

    val viewModel by lazy {
        ViewModelProvider(this, InjectorUtil.getMainFactory()).get(MainViewModel::class.java)
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        mViewBinding.updateBtn.setOnClickListener {
            viewModel.getTokent("s")
        }
        viewModel.text.observe(this, Observer {
            when (it) {
                is HttpResult.Success -> {
                    mViewBinding.mainText.text = it.data
                }
                is HttpResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "这是一个加载弹框", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {

        private const val TAG = "MainActivity"

    }

}