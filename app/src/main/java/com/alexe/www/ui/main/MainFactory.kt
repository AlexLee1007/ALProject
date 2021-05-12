package com.alexe.www.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexe.www.repository.UserRepository

/**
 * @package: com.alexe.www.ui.main
 * @author: Alex Lee
 * @date: 2021/5/7 11:38
 * @des: <b>此处描述当前类</b>
 */
class MainFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository) as T
    }

}