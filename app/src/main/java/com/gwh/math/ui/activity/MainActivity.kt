package com.gwh.math.ui.activity

import android.app.Activity
import android.content.Intent
import com.gwh.math.R
import com.gwh.math.databinding.ActivityMainBinding
import com.gwh.math.utils.CacheUtil
import com.vip.base.activity.BaseActivity
import com.vip.base.util.immersive


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {


    override fun initData() {


       // statusBarColor(Color.TRANSPARENT)
        immersive()
        CacheUtil.setDiJiGuan(0)


        hintActionBar()


        val musicStatus = CacheUtil.getMusicStatus()
        if (musicStatus){
            mBinding.ivMusic.text = "Music/open"
        }else{
            mBinding.ivMusic.text = "Music/close"
        }



        mBinding.ivRongyi.setOnClickListener {
            CacheUtil.setGameStatus("1")
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
            finish()
        }

        mBinding.ivKunnan.setOnClickListener {
            CacheUtil.setGameStatus("2")
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
            finish()
        }

        mBinding.ivDiyu.setOnClickListener {
            CacheUtil.setGameStatus("3")
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
            finish()
        }







        mBinding.ivSetting2.setOnClickListener {

            WebViewActivity.start(this,"Privacy Agreement","https://www.freeprivacypolicy.com/live/43931d46-c0af-4e1c-a67c-2a0b23c99df4")
          //  startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
        }



        if ( CacheUtil.getMusicStatus()){
            mBinding.ivMusicStatus.setImageResource(R.mipmap.ic_music_open)
        }else{
            mBinding.ivMusicStatus.setImageResource(R.mipmap.ic_music_close)
        }


        mBinding.ivMusicStatus.setOnClickListener {
            val musicStatus = CacheUtil.getMusicStatus()
            if (musicStatus){
               // mBinding.ivMusic.text = "Music/close"
                mBinding.ivMusicStatus.setImageResource(R.mipmap.ic_music_close)
            }else{
                //mBinding.ivMusic.text = "Music/open"
                mBinding.ivMusicStatus.setImageResource(R.mipmap.ic_music_open)
            }
            CacheUtil.setMusicStatus(!musicStatus)
        }


        mBinding.ivPlay.setOnClickListener{
//            startActivity(Intent(this@MainActivity, PlayerActivity::class.java))
            startActivity(Intent(this@MainActivity, Game2Activity::class.java))
            finish()
        }

        mBinding.ivSetting.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingActivity::class.java))
        }


        mBinding.ivXieyi.setOnClickListener {
            startActivity(Intent(this@MainActivity, YinSiXieYiActivity::class.java))
        }

        mBinding.ivMusic.setOnClickListener {
            val musicStatus = CacheUtil.getMusicStatus()
            if (musicStatus){
                mBinding.ivMusic.text = "Music/close"
            }else{
                mBinding.ivMusic.text = "Music/open"
            }
            CacheUtil.setMusicStatus(!musicStatus)

        }


    }





    private inline fun <reified activity : Activity> start() {
        startActivity(Intent(this, activity::class.java))
    }


}
