package com.gwh.numberoperation

import android.graphics.Color
import com.vip.base.BaseApp
import com.vip.base.config.BaseConfig

/**
 *AUTHOR:AbnerMing
 *DATE:2022/8/30
 *INTRODUCE:
 */
class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        // 初始化标题栏
        BaseConfig.apply {
            actionBarBg = Color.TRANSPARENT
            titleColor = R.color.tv_333
            statusBarColor = R.color.white
            statusBarDarkMode = true
            leftIcon = R.mipmap.ic_back_black
        }
    }
}
