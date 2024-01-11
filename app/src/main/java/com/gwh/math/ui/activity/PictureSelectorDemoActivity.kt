package com.gwh.math.ui.activity

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.gwh.math.R
import com.gwh.math.databinding.ActivityPictureSelectorDemoBinding
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.vip.base.activity.BaseActivity
import java.util.ArrayList

class PictureSelectorDemoActivity : BaseActivity<ActivityPictureSelectorDemoBinding>(R.layout.activity_picture_selector_demo) {
    override fun initData() {
        setBarTitle("选择图片")

        mBinding.click = ProxyClick()
    }

    private fun selectPic() {
        PictureSelector.create(this@PictureSelectorDemoActivity)
            .openSystemGallery(SelectMimeType.ofImage())
            .forSystemResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>?) {
                    LogUtils.d("====", GsonUtils.toJson(result))
                }

                override fun onCancel() {
                    LogUtils.d("====", "onCancel")
                }
            })
    }

    inner class ProxyClick {
        fun openLoaclPic() {
            LogUtils.d("====", "openLoaclPic")
            ToastUtils.showShort("click")
            selectPic()
        }
    }
}
