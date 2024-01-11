package com.gwh.math.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gwh.math.data.model.bean.UserInfo
import com.vip.base.state.ResultState

class DemoRequestData  {
    var loginResult = MutableLiveData<ResultState<UserInfo>>()

    fun loginReq(userName: String, passWord: String) {


    }
}
