package com.alexe.www.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexe.www.http.ApiResponse
import com.alexe.www.http.HttpResult
import com.alexe.www.http.IBaseResponse
import com.alexe.www.repository.UserRepository
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            val value = userRepository.getTokent(serialId);
            if (value.code == ApiResponse.SUCCESS_CODE) {
                text.value = HttpResult.Success(value.body)
            } else {
                text.value = HttpResult.Error(null, code = value.code, message = value.msg)
            }
        }
    }


    companion object {

        private const val TAG = "MainViewModel"

    }

}
