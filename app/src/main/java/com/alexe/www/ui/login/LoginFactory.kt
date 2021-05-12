package com.alexe.www.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexe.www.repository.UserRepository

/**
 * @package: com.alexe.www.repository
 * @author: Alex Lee
 * @date: 2021/5/7 9:20
 * @des: <b>此处描述当前类</b>
 */
class LoginFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }

}