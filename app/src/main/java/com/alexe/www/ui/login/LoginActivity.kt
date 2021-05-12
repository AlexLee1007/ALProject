package com.alexe.www.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alexe.base.base.BaseActivity
import com.alexe.www.databinding.ActivityLoginBinding
import com.alexe.www.ui.InjectorUtil

/**
 * @package: com.alexe.www.ui.login
 * @author: Alex Lee
 * @date: 2021/4/29 14:41
 * @des: <b>此处描述当前类</b>
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    val viewModel by lazy {
        ViewModelProvider(this, InjectorUtil.getLoginFactory()).get(LoginViewModel::class.java)
    }

    override fun initLayout(savedInstanceState: Bundle?) {

    }

    companion object {

        private const val TAG = "LoginActivity"

    }

}