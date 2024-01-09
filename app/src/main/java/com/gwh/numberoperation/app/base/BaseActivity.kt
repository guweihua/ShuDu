package com.gwh.numberoperation.app.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

import java.lang.ref.WeakReference

/**
 * @author admin
 * @date 2018/11/19
 * @desc BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    /** 当前Activity的弱引用，防止内存泄露  */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 布局文件id
     */
    @LayoutRes
    protected abstract fun attachLayoutRes(): Int

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化数据
     */
    open fun initListener() {}

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        super.onCreate(savedInstanceState)

        activityWR = WeakReference(this)
     //  ActivityCollector.pushTask(activityWR)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // 强制竖屏
        setContentView(attachLayoutRes())
  //      setStatusBarBackground(R.color.white)
//        if (useEventBus()) {
//            EventBus.getDefault().register(this)
//        }
        initView()
        initData()
        start()
        initListener()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            // 如果不是落在EditText区域，则需要关闭输入法
//            if (KeyBoardUtil.isHideKeyboard(v, ev)) {
//                KeyBoardUtil.hideKeyBoard(this, v)
//            }
        }
        return super.dispatchTouchEvent(ev)
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            android.R.id.home -> {
//                onBackPressed()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onDestroy() {
        super.onDestroy()
//        ActivityCollector.removeTask(activityWR)
//        if (useEventBus()) {
//            EventBus.getDefault().unregister(this)
//        }
//        CommonUtil.fixInputMethodManagerLeak(this)
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    open fun onMessageEvent(messageEvent: MessageEvent) {
//    }

    /**
     * 设置状态栏背景色
     */
//    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
//        ImmersionBar.with(this)
//            //.autoStatusBarDarkModeEnable(true, 0.2f)
//            .statusBarColor(statusBarColor)
//            .fitsSystemWindows(true)
//            .init()
//    }
}
