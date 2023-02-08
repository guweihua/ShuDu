package com.gwh.walkplanet.ui.activity

import android.content.Intent
import android.os.Handler
import com.gwh.walkplanet.R
import com.gwh.walkplanet.databinding.ActivitySplashBinding
import com.vip.base.activity.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initData() {
        setBarTitle("")
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


        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 1500)
    }
}
