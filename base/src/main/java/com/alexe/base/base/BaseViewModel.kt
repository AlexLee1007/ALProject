package com.alexe.base.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @package: com.alexe.base.base
 * @author: Alex Lee
 * @date: 2021/3/31 11:50
 * @des: <b>此处描述当前类</b>
 */
open class BaseViewModel : ViewModel(){
    public fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }
}