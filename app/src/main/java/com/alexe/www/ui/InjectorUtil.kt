package com.alexe.www.ui

import com.alexe.www.repository.UserRepository
import com.alexe.www.ui.login.LoginFactory
import com.alexe.www.ui.main.MainFactory

/**
 * @package: com.alexe.www.repository
 * @author: Alex Lee
 * @date: 2021/5/7 9:09
 * @des: <b>此处描述当前类</b>
 */
object InjectorUtil {

    private fun getUserRepository() = UserRepository.getInstance();

    fun getMainFactory() = MainFactory(getUserRepository())

    fun getLoginFactory() = LoginFactory(getUserRepository())


}