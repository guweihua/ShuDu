package com.gwh.numberoperation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gwh.numberoperation.data.model.bean.UserInfo
import com.gwh.numberoperation.network.apiService
import com.vip.base.ext.request
import com.vip.base.state.ResultState
import com.vip.base.viewmodel.BaseViewModel

class DemoRequestData  {
    var loginResult = MutableLiveData<ResultState<UserInfo>>()

    fun loginReq(userName: String, passWord: String) {


    }
}
