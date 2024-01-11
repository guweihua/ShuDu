package com.gwh.math.ui.activity

import com.gwh.math.R
import com.gwh.math.databinding.ActivityTxtBinding
import com.vip.base.activity.BaseActivity
import com.vip.base.util.immersive

class TxtActivity :  BaseActivity<ActivityTxtBinding>(R.layout.activity_txt){
    override fun initData() {

        immersive()
        hintActionBar()


        mBinding.ivBack.setOnClickListener {
            finish()
        }

    }

}