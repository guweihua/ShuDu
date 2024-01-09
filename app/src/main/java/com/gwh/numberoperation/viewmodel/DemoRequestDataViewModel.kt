package com.gwh.numberoperation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gwh.numberoperation.data.model.bean.UserInfo
import com.gwh.numberoperation.network.apiService
import com.gwh.numberoperation.network.stateCallback.CollectUiState
import com.vip.base.ext.request
import com.vip.base.state.ResultState
import com.vip.base.viewmodel.BaseViewModel

class DemoRequestDataViewModel : BaseViewModel() {

    var loginResult = MutableLiveData<ResultState<UserInfo>>()

    // 收藏文章
    val collectUiState: MutableLiveData<CollectUiState> = MutableLiveData()

    private val repository by lazy {
        getRepository<DemoRequestData>()
    }

    fun loginReq(userName: String, passWord: String) {
        request({ apiService.login(userName, passWord) }, {
            loginResult
        }, {
        })
//        request({ apiService.collect(10) }, {
//            LogUtils.d("====", "success")
//            val uiState = CollectUiState(isSuccess = true, collect = false, id = 10)
//            collectUiState.value = uiState
//        }, {
//            LogUtils.d("====", "error"+it)
//            val uiState = CollectUiState(isSuccess = false, collect = false, errorMsg = it.errorMsg, id = 10)
//            collectUiState.value = uiState
//        })
    }

    // LiveData方式返回数据，类型自定义，对象，集合或者其他类型，请以实际业务为准
    val mResultLiveData = MutableLiveData<String>()
}
