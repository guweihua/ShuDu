package com.gwh.walkplanet.ui.activity
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.gwh.walkplanet.R
import com.gwh.walkplanet.databinding.ActivityMainBinding
import com.gwh.walkplanet.ui.activity.demo.TestViewModelActivity
import com.gwh.walkplanet.ui.activity.demo.bar.ActionBarImageActivity
import com.gwh.walkplanet.ui.activity.demo.bar.ActionBarTextActivity
import com.gwh.walkplanet.ui.activity.demo.bind.DataBindActivity
import com.gwh.walkplanet.ui.activity.demo.bus.BusActivity
import com.gwh.walkplanet.ui.activity.demo.page.TestPagerActivity
import com.gwh.walkplanet.ui.activity.demo.repository.TestRepositoryActivity
import com.gwh.walkplanet.ui.activity.demo.status.StatusBarActivity
import com.gwh.walkplanet.ui.activity.demo.status.StatusBarChangeActivity
import com.vip.base.activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initData() {
        setBarTitle("首页")
        hintLeftMenu()
        mBinding.click = ProxyClick()
        mBinding.btSelectPic.setOnClickListener {
            start<PictureSelectorDemoActivity>()
        }

        mBinding.btnActivity.setOnClickListener {
            // ViewModel使用跳转
            Toast.makeText(
                this,
                "查看MainActivity即可",
                Toast.LENGTH_SHORT
            ).show()
        }

        mBinding.btnViewModel.setOnClickListener {
            // ViewModel使用跳转
            start<TestViewModelActivity>()
        }

        mBinding.btnRepository.setOnClickListener {
            // Repository使用
            start<TestRepositoryActivity>()
        }

        mBinding.btnDataBind.setOnClickListener {
            // Repository使用
            start<DataBindActivity>()
        }

        mBinding.btnActionBarText.setOnClickListener {
            // 标题栏左侧文字按钮
            start<ActionBarTextActivity>()
        }

        mBinding.btnActionBarImage.setOnClickListener {
            // 标题栏左侧图片按钮
            start<ActionBarImageActivity>()
        }

        mBinding.btnStatusBar.setOnClickListener {
            // 透明状态栏
            start<StatusBarActivity>()
        }
        mBinding.btnStatusBarChange.setOnClickListener {
            // 状态栏改变
            start<StatusBarChangeActivity>()
        }

        mBinding.btnPager.setOnClickListener {
            // BaseFragmentPagerAdapter使用
            start<TestPagerActivity>()
        }

        mBinding.btnFragment.setOnClickListener {
            // Fragment简单继承
            start<TestPagerActivity>()
        }

        mBinding.btnFragmentViewModel.setOnClickListener {
            // Fragment  ViewModel 形式继承 打开后直接看 TestViewModelPagerFragment
            start<TestPagerActivity>()
        }

        mBinding.btnBus.setOnClickListener {
            // 事件总线
            start<BusActivity>()
        }
    }

    private inline fun <reified activity : Activity> start() {
        startActivity(Intent(this, activity::class.java))
    }

    inner class ProxyClick() {
        fun testRequestData() {
            start<DemoRequestDataActivity>()
        }
    }
}
