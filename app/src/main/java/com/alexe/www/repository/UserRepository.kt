package com.alexe.www.repository

import com.alexe.base.base.BaseRepository
import com.alexe.www.http.Api
import com.alexe.www.http.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @package: com.alexe.www.ui.main
 * @author: Alex Lee
 * @date: 2021/4/9 9:58
 * @des: <b>用户数据仓库</b>
 */
class UserRepository : BaseRepository() {

    suspend fun getTokent(serialId: String) = withContext(Dispatchers.IO) {
        Api.create<ApiService>().getTokent("ANDROID-TERMINAL", serialId)
    }

    suspend fun getDeviceInfo() = withContext(Dispatchers.IO) {
        Api.create<ApiService>().getDeviceInfo("ANDROID-TERMINAL",
                "iaZvMs74x\\/r19FwhbEuJaTBppiWVmRAxoV0zUGjDEiFzHxtWE09RTFdOjM4ci0xzt0pqcT+nIWBKLzGL39iSXyR1TwAglxSAj7\\/f6RvNun9lKBgOnPMkeklA4km6j3iaRJh+NScuqMZQKhdNFaTt\\/5RK2R63BRaiBPz++r9LlzI/=SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSE")
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