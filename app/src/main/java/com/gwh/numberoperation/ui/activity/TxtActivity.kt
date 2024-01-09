package com.gwh.numberoperation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gwh.numberoperation.R
import com.gwh.numberoperation.databinding.ActivityTxtBinding
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