package com.gwh.math.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.gwh.math.R
import com.gwh.math.databinding.ActivityGame2Binding
import com.gwh.math.utils.CacheUtil
import com.vip.base.activity.BaseActivity
import com.vip.base.util.immersive
import java.text.SimpleDateFormat
import java.util.*


class GameActivity : BaseActivity<ActivityGame2Binding>(R.layout.activity_game2) {

    var soundPool: SoundPool? = null

    var isPlayering = true

    var timer: CountDownTimer? = null
    var examLength = 5 * 60L
    override fun initData() {
        immersive()

        hintActionBar()
        val gameStatus = CacheUtil.getGameStatus()
        if (gameStatus =="2"){
            examLength = 5*60*1000L
        }else if (gameStatus == "3"){
            examLength = 5*60*1000L
        }else{
            examLength = 10*60*1000L
        }
//
//        if (AppUtils.isAppDebug()){
//            examLength = 10*1000L
//        }


        mBinding.btAgain.setOnClickListener(View.OnClickListener { v: View? ->
            mBinding.viewShudu.play()
        })


        isPlayering = CacheUtil.getMusicStatus()

        if (isPlayering) {
            mBinding.ivPlay.setImageResource(R.mipmap.ic_music_open)
        } else {
            mBinding.ivPlay.setImageResource(R.mipmap.ic_music_close)
        }


        soundPool = SoundPool(1, AudioManager.STREAM_SYSTEM, 1)
        soundPool?.load(this, R.raw.music_bg, 1)
        soundPool?.setOnLoadCompleteListener { soundPool, sampleId, status ->
            soundPool.play(1, 1f, 1f, 0, -1, 1f)
            if (!isPlayering) {
                soundPool?.autoPause()
            }
        }


        initListener()
        countdown()

        mBinding.viewShudu.setCallBack(object : CalBack{
            override fun a(type:String) {

                if (type=="1"){
                    if (timer!=null){
                        timer!!.cancel()
                    }
                }else{
                    startActivity(Intent(this@GameActivity, MainActivity::class.java))
                    finish()
                }
            }
        })
    }

    interface CalBack{
        fun a(type:String)
    }


    private fun initListener() {

        mBinding.ivSetting2.setOnClickListener {
            startActivity(Intent(this@GameActivity, MainActivity::class.java))
            finish()
        }

        mBinding.tvTime.setOnClickListener {

        }

        mBinding.ivPlay.setOnClickListener {
            if (isPlayering) {
                mBinding.ivPlay.setImageResource(R.mipmap.ic_music_close)
                soundPool?.autoPause()
            } else {
                mBinding.ivPlay.setImageResource(R.mipmap.ic_music_open)
                soundPool?.autoResume()
            }

            isPlayering = !isPlayering

            CacheUtil.setMusicStatus(isPlayering)
        }

    }


    private fun countdown() {
        if (timer != null) {
            timer!!.cancel()
        }

        timer = object : CountDownTimer(examLength,  1000) {
            override fun onTick(millisUntilFinished: Long) {

                mBinding.tvTime.text = second2Time(millisUntilFinished/1000)
            }

            override fun onFinish() {
                ToastUtils.showShort("stop")
                mBinding.tvTime.text = "00:00"
                showDialogs()

                timer!!.cancel()
            }

        }
        timer!!.start()
    }

    private fun showDialogs() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("The countdown has ended, please start over." )
            .setPositiveButton("Ensure") { arg0, arg1 ->
                countdown()
                mBinding.viewShudu.play()
                arg0.cancel()
            }
            .setNeutralButton("Cancle"){_,_->
                startActivity(Intent(this@GameActivity, MainActivity::class.java))
                finish()
            }

//            .setOnCancelListener {
//                startActivity(Intent(this@GameActivity, MainActivity::class.java))
//                finish()
//            }
        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun getDateAndHourMinuteTime(dateMillis: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date(dateMillis))
    }

    fun second2Time(second: Long?): String? {
        if (second == null || second < 0) {
            return "00:00"
        }
        val h = second / 3600
        val m = second % 3600 / 60
        val s = second % 60
        var str = ""
        if (h > 0) {
            str = (if (h < 10) "0$h" else h).toString() + ":"
        }
        str += (if (m < 10) "0$m" else m).toString() + ":"
        str += if (s < 10) "0$s" else s
        return str
    }


    override fun onDestroy() {

        soundPool?.autoPause()
        soundPool?.release()
        soundPool = null

        if (timer != null) {
            timer!!.cancel()
        }

        super.onDestroy()
    }
}