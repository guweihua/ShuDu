package com.gwh.numberoperation.utils

import com.blankj.utilcode.util.SPUtils

object CacheUtil {
    /**
     * 获取保存的账户信息
     */
//    fun getUser(): UserInfo? {
//        val kv = MMKV.mmkvWithID("app")
//        val userStr = kv.decodeString("user")
//        return if (TextUtils.isEmpty(userStr)) {
//           null
//        } else {
//            Gson().fromJson(userStr, UserInfo::class.java)
//        }
//    }

    // 难易程度 "简单" 1, "普通" 2, "困难" 3, "地狱" 4
    fun getNanYiChengDu():Int{
        return  SPUtils.getInstance().getInt("key_nanyichengdu",1)
    }

    fun setNanYiChengDu(num:Int){
        SPUtils.getInstance().put("key_nanyichengdu",num)
    }


    //当前难度 第几关
    fun getDijiGaun():Int{
        return  SPUtils.getInstance().getInt("key_dijiguan",0)
    }

    fun setDiJiGuan(num:Int){
        SPUtils.getInstance().put("key_dijiguan",num)
    }

    fun  getNumberRandom():Int{
        return  SPUtils.getInstance().getInt("key_number_random",9)
    }

    fun setNumberRandom(num:Int){
        SPUtils.getInstance().put("key_number_random",num)
    }

    fun getMusicStatus():Boolean{
        return    SPUtils.getInstance().getBoolean("music_status",true)
    }

    fun  setMusicStatus(status:Boolean){
        SPUtils.getInstance().put("music_status",status)
    }

}
