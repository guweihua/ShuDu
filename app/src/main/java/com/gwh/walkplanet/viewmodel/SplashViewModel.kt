package com.gwh.walkplanet.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vip.base.viewmodel.BaseViewModel

class SplashViewModel : BaseViewModel() {
    // LiveData方式返回数据，类型自定义，对象，集合或者其他类型，请以实际业务为准
    val mResultLiveData = MutableLiveData<String>()
}
