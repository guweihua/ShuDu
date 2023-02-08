package com.gwh.walkplanet.ui.activity

import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.gwh.walkplanet.R
import com.gwh.walkplanet.databinding.ActivityDemoRequestDataBinding
import com.gwh.walkplanet.viewmodel.DemoRequestDataViewModel
import com.vip.base.activity.BaseVMActivity

class DemoRequestDataActivity : BaseVMActivity<ActivityDemoRequestDataBinding, DemoRequestDataViewModel>(R.layout.activity_demo_request_data) {

    override fun initVMData() {
        setBarTitle("请求数据")
        mBinding.click = ProxyClick()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        mViewModel.loginResult.observe(this) {
        }

        mViewModel.collectUiState.observe(
            this,
            Observer {
                if (it.isSuccess) {
                } else {
                }
            }
        )
    }

    inner class ProxyClick() {
        fun requestData() {
            LogUtils.d("====", "requestData")
            mViewModel.loginReq("1111", "1111")
        }
    }
}
