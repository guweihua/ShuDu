package com.gwh.numberoperation.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.gwh.numberoperation.R
import com.gwh.numberoperation.data.model.bean.ShowNumInfo

class MainAdapter (data: MutableList<ShowNumInfo>?, layoutResId: Int = R.layout.item_index_show) : BaseQuickAdapter<ShowNumInfo, BaseViewHolder>(layoutResId, data){
    override fun convert(holder: BaseViewHolder, item: ShowNumInfo) {
        holder.setText(R.id.item_tv_num,item.content)
           // .setBackgroundResource(R.id.item_tv_num,if (item.isSelect) R.drawable.bg_radius_blue_4 else R.drawable.bg_radius_gray_4)
    }
}