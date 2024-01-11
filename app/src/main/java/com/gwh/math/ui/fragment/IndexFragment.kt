package com.gwh.math.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.gwh.math.R
import com.gwh.math.data.model.bean.ShowNumInfo
import com.gwh.math.databinding.FragmentIndexBinding
import com.gwh.math.ext.init
import com.gwh.math.ui.adapter.MainAdapter
import com.gwh.math.utils.Student
import com.lxj.xpopup.XPopup
import com.vip.base.fragment.BaseFragment
import java.util.*

class IndexFragment : BaseFragment<FragmentIndexBinding>(R.layout.fragment_index) {


    var number_random: Int = 10

    val mAdapter: MainAdapter by lazy { MainAdapter(arrayListOf()) }

    override fun initData() {

        // setBarTitle("首页")
        // hintLeftMenu()
        //  mBinding.click = ProxyClick()


        mBinding.rvShowNum.init(GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false), mAdapter)


        showDialog()

        initListener()


    }


    private fun showDialog() {

        XPopup.Builder(requireContext())
            .dismissOnBackPressed(false)
            .dismissOnTouchOutside(false)
            .asBottomList(
                "请选择难度", arrayOf<String>("简单", "普通", "困难", "地狱")
            ) { position, text ->
                if (position == 0) {
                    number_random = 10
                } else if (position == 1) {
                    number_random = 22
                } else if (position == 2) {
                    number_random = 33
                } else if (position == 3) {
                    number_random = 100
                }
                mBinding.btSelectNandu.text = text
                clearData()
                getNewData()

            }
            .show()
    }

    private fun getNewData() {

        val expression = Student.getExpression(number_random, 30)
        LogUtils.d("getData", expression)
        getData(expression)

    }

    private fun initListener() {

        mBinding.btSelectNandu.setOnClickListener {
            showDialog()
        }

        mAdapter.run {
            setOnItemClickListener { adapter, view, position ->
                val showNumInfo = adapter.data[position] as ShowNumInfo
                showNumInfo.isSelect = !showNumInfo.isSelect

                val tv1 = mBinding.tvResult1.text.toString()
                val tv2 = mBinding.tvResult2.text.toString()
                val tv3 = mBinding.tvResult3.text.toString()

                if (showNumInfo.isSelect) {
                    if (TextUtils.isEmpty(tv1)) {
                        mBinding.tvResult1.text = showNumInfo.content
                    } else if (TextUtils.isEmpty(tv2)) {
                        mBinding.tvResult2.text = showNumInfo.content
                    } else if (TextUtils.isEmpty(tv3)) {
                        mBinding.tvResult3.text = showNumInfo.content
                    }
                } else {

                    if (tv1 == showNumInfo.content) {
                        mBinding.tvResult1.text = ""
                    } else if (tv2 == showNumInfo.content) {
                        mBinding.tvResult2.text = ""
                    } else if (tv3 == showNumInfo.content) {
                        mBinding.tvResult3.text = ""
                    }


                }
                notifyDataSetChanged()
            }
        }

        mBinding.btGetnew.setOnClickListener {
            clearData()
            getNewData()
        }


        mBinding.tvFuhao1.setOnClickListener {
            val text = mBinding.tvResultFuhao1.text.toString()
            val text2 = mBinding.tvResultFuhao2.text.toString()
            if (text.isEmpty()) {
                mBinding.tvResultFuhao1.text = mBinding.tvFuhao1.text.toString()
            } else if (text2.isEmpty()) {
                mBinding.tvResultFuhao2.text = mBinding.tvFuhao1.text.toString()
            } else if (mBinding.tvFuhao1.text.toString() == text) {
                mBinding.tvResultFuhao1.text = ""
            } else if (mBinding.tvFuhao1.text.toString() == text2) {
                mBinding.tvResultFuhao2.text = ""
            }
        }

        mBinding.tvFuhao2.setOnClickListener {
            val text = mBinding.tvResultFuhao1.text.toString()
            val text2 = mBinding.tvResultFuhao2.text.toString()
            if (text.isEmpty()) {
                mBinding.tvResultFuhao1.text = mBinding.tvFuhao2.text.toString()
            } else if (text2.isEmpty()) {
                mBinding.tvResultFuhao2.text = mBinding.tvFuhao2.text.toString()
            } else if (mBinding.tvFuhao2.text.toString() == text) {
                mBinding.tvResultFuhao1.text = ""
            } else if (mBinding.tvFuhao2.text.toString() == text2) {
                mBinding.tvResultFuhao2.text = ""
            }
        }

        mBinding.tvResultFuhao1.setOnClickListener {
            mBinding.tvResultFuhao1.text = ""
        }

        mBinding.tvResultFuhao2.setOnClickListener {
            mBinding.tvResultFuhao1.text = ""
        }



        mBinding.tvResult1.setOnClickListener {
            val text = mBinding.tvResult1.text.toString()
            if (text.isEmpty()) {

            } else {
                mBinding.tvResult1.text = ""
                modifyAdapterData(text)
            }
        }

        mBinding.tvResult2.setOnClickListener {
            val text = mBinding.tvResult2.text.toString()
            if (text.isEmpty()) {

            } else {
                mBinding.tvResult2.text = ""
                modifyAdapterData(text)
            }
        }

        mBinding.tvResult3.setOnClickListener {
            val text = mBinding.tvResult3.text.toString()
            if (text.isEmpty()) {

            } else {
                mBinding.tvResult3.text = ""
                modifyAdapterData(text)
            }
        }

        mBinding.btCheck.setOnClickListener {
            val tv1 = mBinding.tvResult1.text.toString()
            val fuhao1 = mBinding.tvResultFuhao1.text.toString()
            val fuhao2 = mBinding.tvResultFuhao2.text.toString()
            val tv2 = mBinding.tvResult2.text.toString()
            val tv3 = mBinding.tvResult3.text.toString()

            if (TextUtils.isEmpty(tv1) || TextUtils.isEmpty(tv2) || TextUtils.isEmpty(tv3) || TextUtils.isEmpty(fuhao1) || TextUtils.isEmpty(
                    fuhao2
                )
            ) {
                ToastUtils.showShort("请选择数字或运算符")
                return@setOnClickListener
            }

            var result = 0
            var operator1: Char = getOperator(fuhao1)
            var operator2: Char = getOperator(fuhao2)


            var strTest = tv1 + "" + operator1 + "" + tv2 + "" + operator2 + tv3

            var calculate = Student.calculate(strTest)

            if (calculate.contains(".")) {
                calculate = calculate.split(".")[0]
            }

            LogUtils.d("=======", "calculate:" + calculate)


            if (calculate == mBinding.tvResult.text.toString()) {
                ToastUtils.showShort("计算成功")
            } else {
                ToastUtils.showShort("计算失败")
            }


        }

    }

    private fun clearData() {

        mBinding.tvResult1.text = ""
        mBinding.tvResult2.text = ""
        mBinding.tvResult3.text = ""

        mBinding.tvResultFuhao1.text = ""
        mBinding.tvResultFuhao2.text = ""

        mBinding.tvResult.text = ""

    }

    fun getOperator(s: String): Char {
        val signal = charArrayOf('+', '-', '*', '/')

        var i = 0
        if (s == "+") {
            i = 0
        } else if (s == "-") {
            i = 1
        } else if (s == "*") {
            i = 2
        } else {
            i = 3
        }

        return signal[i]
    }


    private fun modifyAdapterData(text: String) {

        for (i in mAdapter.data.indices) {
            val showNumInfo = mAdapter.data[i]
            if (showNumInfo.content == text) {
                showNumInfo.isSelect = false
            }
        }
        mAdapter.notifyDataSetChanged()

    }


    private fun getData(expression: String) {

        LogUtils.d("getData2", expression)


        val split1 = expression.split("=")
        val s1 = split1[0]
        val s2 = split1[1]
        mBinding.tvResult.text = s2

        setFuHao(expression)

        LogUtils.d("getData3", GsonUtils.toJson(s1))

        var listNum = mutableListOf<String>()

        var c = s1.toString()


        if (c.contains("+")) {
            c = c.replace("+", " ")
        }

        if (c.contains("-")) {
            c = c.replace("-", " ")
        }

        if (c.contains("*")) {
            c = c.replace("*", " ")
        }

        if (c.contains("÷")) {
            c = c.replace("÷", " ")
        }




        LogUtils.d("getData33", GsonUtils.toJson(c))
        val split = c.split(" ")
        for (i in split.indices) {
            listNum.add(split[i])
        }




        LogUtils.d("getData33", GsonUtils.toJson(split))



        LogUtils.d("getData", listNum)

        val numData = setNumData(listNum)

        LogUtils.d("getData", GsonUtils.toJson(numData))


        mAdapter.setList(numData);


    }

    private fun setNumData(split: List<String>): MutableList<ShowNumInfo> {
        var listData = mutableListOf<ShowNumInfo>()

        var ss = mutableListOf<String>()

        ss.add(split[0])
        ss.add(split[1])
        ss.add(split[2])


        val showNumInfo = ShowNumInfo()
        showNumInfo.content = split[0]
        listData.add(showNumInfo)

        val showNumInfo2 = ShowNumInfo()

        var toString = (Random().nextInt(number_random)).toString()
        if (ss.contains(toString)) {
            toString = (toString.toInt() + 1).toString()
        }
        ss.add(toString)

        showNumInfo2.content = toString
        listData.add(showNumInfo2)

        val showNumInfo3 = ShowNumInfo()
        var toString1 = (Random().nextInt(number_random) + 8).toString()
        if (ss.contains(toString1)) {
            toString1 = (toString1.toInt() + 1).toString()
        }
        ss.add(toString1)

        showNumInfo3.content = toString1
        listData.add(showNumInfo3)

        val showNumInfo4 = ShowNumInfo()
        showNumInfo4.content = split[1]
        listData.add(showNumInfo4)

        val showNumInfo5 = ShowNumInfo()
        showNumInfo5.content = split[2]
        listData.add(showNumInfo5)

        val showNumInfo6 = ShowNumInfo()

        var toString2 = (Random().nextInt(number_random) + 16).toString()
        if (ss.contains(toString2)) {
            toString2 = (toString2.toInt() + 1).toString()
        }
        ss.add(toString2)

        showNumInfo6.content = toString2
        listData.add(showNumInfo6)

        return listData
    }

    private fun setFuHao(expression: String) {
        var listFuHao = mutableListOf<String>()

        if (expression.contains("+")) {
            listFuHao.add("+")
        }

        if (expression.contains("-")) {
            listFuHao.add("-")
        }

        if (expression.contains("*")) {
            listFuHao.add("*")
        }

        if (expression.contains("÷")) {
            listFuHao.add("÷")
        }

        if (listFuHao.isNotEmpty()) {
            mBinding.tvFuhao1.text = listFuHao[0]
            mBinding.tvFuhao2.text = listFuHao[1]
        }

    }


    fun newInstance(): IndexFragment {
        val indexFragment = IndexFragment()
        val args = Bundle()
        indexFragment.arguments = args
        return indexFragment
    }


}