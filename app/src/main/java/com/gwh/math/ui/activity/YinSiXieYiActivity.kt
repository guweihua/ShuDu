package com.gwh.math.ui.activity

import com.gwh.math.R
import com.gwh.math.databinding.ActivityYinSiXieYiBinding
import com.vip.base.activity.BaseActivity
import com.vip.base.util.immersive

class YinSiXieYiActivity :  BaseActivity<ActivityYinSiXieYiBinding>(R.layout.activity_yin_si_xie_yi) {


    override fun initData() {
        immersive()
        hintActionBar()


        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
}