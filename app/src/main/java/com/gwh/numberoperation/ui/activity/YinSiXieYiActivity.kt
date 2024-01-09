package com.gwh.numberoperation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gwh.numberoperation.R
import com.gwh.numberoperation.databinding.ActivityYinSiXieYiBinding
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