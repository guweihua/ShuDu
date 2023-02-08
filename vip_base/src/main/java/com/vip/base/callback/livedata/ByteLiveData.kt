package com.vip.base.callback.livedata

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData

/**
 * 作者　: * 时间　: 2019/12/17
 * 描述　:自定义的Short类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class ByteLiveData() : MutableLiveData<Byte>(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun getValue(): Byte {
        return super.getValue() ?: 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ByteLiveData> {
        override fun createFromParcel(parcel: Parcel): ByteLiveData {
            return ByteLiveData(parcel)
        }

        override fun newArray(size: Int): Array<ByteLiveData?> {
            return arrayOfNulls(size)
        }
    }
}
