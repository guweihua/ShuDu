package com.gwh.walkplanet

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
            actionBarBg = R.color.purple_700
            titleColor = R.color.white
            statusBarColor = R.color.purple_700
            statusBarDarkMode = false
            leftIcon = R.mipmap.ic_back
        }
    }
}
