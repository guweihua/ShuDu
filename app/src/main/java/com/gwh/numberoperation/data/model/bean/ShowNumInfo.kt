package com.gwh.numberoperation.data.model.bean
import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 作者　: * 时间　: 2019/12/23
 * 描述　: 账户信息
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ShowNumInfo(
    var isSelect: Boolean = false,
    var content: String = "",

) : Parcelable
