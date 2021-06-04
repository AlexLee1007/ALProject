package com.alexe.www.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexe.www.http.HttpResult
import com.alexe.www.https.call
import com.alexe.www.repository.UserRepository
import kotlinx.coroutines.*

/**
 * @package: com.alexe.www.ui.main
 * @author: Alex Lee
 * @date: 2021/4/9 9:58
 * @des: <b>此处描述当前类</b>
 */
class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    val text: MutableLiveData<HttpResult<String>> by lazy {
        MutableLiveData<HttpResult<String>>()
    }


    fun getTokent(serialId: String) {
        viewModelScope.launch(call { code, message -> text.value = HttpResult.Error(code = code, message = message) }) {
            val value = userRepository.getTokent(serialId)
            text.value = HttpResult.Success(value.data)
        }
    }


    companion object {

        private const val TAG = "MainViewModel"

    }

}
