package com.gwh.walkplanet.ui.activity.demo.bind

import com.gwh.walkplanet.BR
import com.gwh.walkplanet.R
import com.gwh.walkplanet.databinding.ActivityDataBindBinding
import com.vip.base.activity.BaseVMActivity

/**
 *AUTHOR:AbnerMing
 *DATE:2022/9/3
 *INTRODUCE:DataBind
 */
class DataBindActivity :
    BaseVMActivity<ActivityDataBindBinding, DataBindViewModel>(R.layout.activity_data_bind) {

    override fun initVMData() {
        setBarTitle("DataBinding使用")
    }

    override fun getVariableId(): Int {
        return BR.data
    }
}