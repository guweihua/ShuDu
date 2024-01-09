package com.gwh.numberoperation.ui.fragment

import android.os.Bundle
import com.gwh.numberoperation.R
import com.gwh.numberoperation.databinding.FragmentIndexBinding
import com.gwh.numberoperation.databinding.FragmentMyBinding
import com.vip.base.fragment.BaseFragment

class MyFragment : BaseFragment<FragmentMyBinding>(R.layout.fragment_my){
    override fun initData() {

    }


    fun newInstance(): MyFragment {
        val indexFragment = MyFragment()
        val args = Bundle()
        indexFragment.arguments = args
        return indexFragment
    }


}