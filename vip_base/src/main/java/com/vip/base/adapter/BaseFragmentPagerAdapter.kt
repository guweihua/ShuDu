@file:Suppress("FunctionName")

package com.vip.base.adapter

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *AUTHOR:AbnerMing
 *DATE:2022/8/10
 *INTRODUCE:BaseFragmentPagerAdapter父类
 */
fun FragmentActivity.FragmentPagerAdapter(
    fragments: List<Fragment>,
    titles: List<String>? = null
): BaseFragmentPagerAdapter {
    return BaseFragmentPagerAdapter(supportFragmentManager, fragments, titles)
}

fun Fragment.FragmentPagerAdapter(
    fragments: List<Fragment>,
    titles: List<String>? = null
): BaseFragmentPagerAdapter {
    return BaseFragmentPagerAdapter(childFragmentManager, fragments, titles)
}

class BaseFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    var fragments: List<Fragment>,
    var titles: List<String>? = null
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT), Parcelable {

    constructor(parcel: Parcel) : this(
        TODO("fragmentManager"),
        TODO("fragments"),
        parcel.createStringArrayList()
    ) {
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(titles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseFragmentPagerAdapter> {
        override fun createFromParcel(parcel: Parcel): BaseFragmentPagerAdapter {
            return BaseFragmentPagerAdapter(parcel)
        }

        override fun newArray(size: Int): Array<BaseFragmentPagerAdapter?> {
            return arrayOfNulls(size)
        }
    }
}

fun FragmentActivity.FragmentAdapter(
    fragments: List<Fragment>
): FragmentStateAdapter = object : FragmentStateAdapter(this) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

fun Fragment.FragmentAdapter(
    fragments: List<Fragment>
): FragmentStateAdapter = object : FragmentStateAdapter(this) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
