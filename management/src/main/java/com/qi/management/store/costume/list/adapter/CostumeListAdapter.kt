package com.qi.management.store.costume.list.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.qi.management.R
import com.qi.management.bean.CombosBean
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.recyclerview.BaseAdapter
import com.yizhipin.base.recyclerview.BaseViewHolder

/**
 * 服装列表Adapter
 * Creator Qi
 * Date 2019/3/2
 */
class CostumeListAdapter : BaseAdapter<CombosBean, CostumeListViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CostumeListViewHolder {
        return CostumeListViewHolder(View.inflate(p0.context, R.layout.item_costume_list, null))
    }

}

class CostumeListViewHolder(itemView: View) : BaseViewHolder<CombosBean>(itemView) {
    /**图片*/
    val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    /**租金*/
    val rentText = itemView.findViewById<TextView>(R.id.rentText)
    /**拍卖价*/
    val auctionPriceText = itemView.findViewById<TextView>(R.id.auctionPriceText)

    override fun setData(data: CombosBean) {
        imageView.loadUrl(data.imgurl)
        rentText.text = if (data.sellType == 1) "租￥ ${data.price}" else "￥ ${data.price}"
        auctionPriceText.text = if (data.sellType == 1) "拍￥ ${data.paiAmount}" else ""
    }
}