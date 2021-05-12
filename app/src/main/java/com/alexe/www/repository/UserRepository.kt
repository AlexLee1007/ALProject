package com.alexe.www.repository

import com.alexe.base.base.BaseRepository
import com.alexe.www.http.Api
import com.alexe.www.http.ApiService
import com.alexe.www.http.call
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.NullPointerException

/**
 * @package: com.alexe.www.ui.main
 * @author: Alex Lee
 * @date: 2021/4/9 9:58
 * @des: <b>用户数据仓库</b>
 */
class UserRepository : BaseRepository() {

    suspend fun getTokent(serialId: String) = withContext(Dispatchers.IO) {
        call {
            Api.create<ApiService>().getTokent("ANDROID-TERMINAL", serialId)
        }
    }

    suspend fun getUserName() = withContext(Dispatchers.IO) {
        "老王"
    }

    suspend fun getUserSex() = withContext(Dispatchers.IO) {
        "男"
    }


    companion object {

        private var instance: UserRepository? = null;

        fun getInstance(): UserRepository {
            if (instance == null) {
                synchronized(UserRepository::class.java) {
                    if (instance == null) {
                        instance = UserRepository()
                    }
                }
            }
            return instance!!
        }

    }

}