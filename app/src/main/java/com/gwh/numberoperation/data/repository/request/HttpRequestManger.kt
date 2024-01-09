package com.gwh.numberoperation.data.repository.request

import com.gwh.numberoperation.data.model.bean.ApiResponse
import com.gwh.numberoperation.data.model.bean.UserInfo
import com.vip.base.network.AppException
import com.gwh.numberoperation.network.apiService

/**
 * 作者　:
 * 时间　: 2020/5/4
 * 描述　: 处理协程的请求类
 */

val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {

    /**
     * 注册并登陆
     */
    suspend fun register(username: String, password: String): ApiResponse<UserInfo> {
        val registerData = apiService.register(username, password, password)
        // 判断注册结果 注册成功，调用登录接口
        if (registerData.isSucces()) {
            return apiService.login(username, password)
        } else {
            // 抛出错误异常
            throw AppException(registerData.errorCode, registerData.errorMsg)
        }
    }
}
