package com.gwh.math.ui.activity

import android.content.Intent
import com.gwh.math.R
import com.gwh.math.databinding.ActivitySettingBinding
import com.vip.base.activity.BaseActivity
import com.vip.base.util.immersive

class SettingActivity : BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {


    override fun initData() {
        immersive()
        hintActionBar()


        mBinding.ivBack.setOnClickListener {
            finish()
        }


        mBinding.tvAbout.setOnClickListener {
            startActivity(Intent(this@SettingActivity, TxtActivity::class.java))
        }


        mBinding.tvYinsi.setOnClickListener {
            WebViewActivity.start(this,"Privacy Agreement","https://www.freeprivacypolicy.com/live/43931d46-c0af-4e1c-a67c-2a0b23c99df4")

           // startActivity(Intent(this@SettingActivity, YinSiXieYiActivity::class.java))

          //  WebViewActivity.start(this,"隐私协议","www.baidu.com")
        }


    }
}