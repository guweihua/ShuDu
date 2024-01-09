package com.gwh.numberoperation.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.gwh.numberoperation.R
import com.gwh.numberoperation.databinding.ActivitySplashBinding
import com.vip.base.activity.BaseActivity
import com.vip.base.util.statusBarColor

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initData() {
        statusBarColor(Color.TRANSPARENT)
        hintActionBar()
        hintLeftMenu()

//        XXPermissions.with(this)
//            .permission(Permission.CAMERA)
//            .permission(Permission.WRITE_EXTERNAL_STORAGE)
//            .permission(Permission.READ_EXTERNAL_STORAGE)
//            .request(object : OnPermissionCallback {
//                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
//                    if (!allGranted) {
//                        ToastUtils.showShort("部分权限未正常授予")
//                    }
//
//                    Handler().postDelayed({
//                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                    }, 1500)
//                }
//            })


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 1500)
    }
}
