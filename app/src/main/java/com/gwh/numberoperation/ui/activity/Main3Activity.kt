package com.gwh.numberoperation.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.gwh.numberoperation.R
import com.gwh.numberoperation.data.model.bean.TabEntity
import com.gwh.numberoperation.databinding.ActivityMain3Binding
import com.gwh.numberoperation.databinding.ActivityMainBinding
import com.gwh.numberoperation.ui.fragment.IndexFragment
import com.gwh.numberoperation.ui.fragment.MyFragment
import com.vip.base.activity.BaseActivity


class Main3Activity : BaseActivity<ActivityMain3Binding>(R.layout.activity_main3) {


    var fragments: java.util.ArrayList<Fragment> = java.util.ArrayList()
    val mTitles = arrayOf("首页", "我的");
    var mTabEntities: ArrayList<CustomTabEntity> = ArrayList()
    val mIconUnSelectIds = intArrayOf(R.mipmap.main_index_normal, R.mipmap.main_mine_normal);
    val mIconSelectIds = intArrayOf(R.mipmap.main_index_select, R.mipmap.main_mine_select)

    override fun initData() {

        setBarTitle("首页")
        hintLeftMenu()



        val indexFragment = IndexFragment().newInstance()
        val myFragment = MyFragment().newInstance()

        fragments.add(indexFragment)
        fragments.add(myFragment)

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles.get(i), mIconSelectIds.get(i), mIconUnSelectIds.get(i)))
        }

        mBinding.tabLayout.setTabData(mTabEntities, this, R.id.fl_container, fragments)


        mBinding.tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {

                if (position == 0){
                    setBarTitle("首页")
                }else{
                    setBarTitle("我的")
                }

            }

            override fun onTabReselect(position: Int) {
            }
        })


    }





    private inline fun <reified activity : Activity> start() {
        startActivity(Intent(this, activity::class.java))
    }


}
