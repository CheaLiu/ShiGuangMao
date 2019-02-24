package com.qi.management.store.wedding_photography.comboslist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.R
import com.qi.management.bean.CombosBean
import com.qi.management.store.wedding_photography_detail.view.CombosDetailActivity.Companion.PARAM_COMBOS_BEAN
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.provider.router.RouterPath

class CombosListAdapter : RecyclerView.Adapter<CombosItemViewHolder>() {

    private val mData: MutableList<CombosBean> = arrayListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CombosItemViewHolder {
        return CombosItemViewHolder(View.inflate(p0.context, R.layout.item_combos_list, null))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: CombosItemViewHolder, p1: Int) {
        val combosBean = mData[p1]
        p0.setData(combosBean)
    }

    fun add(data: MutableList<CombosBean>) {
        if (data.size == 0) return
        val start = mData.size
        this.mData.addAll(data)
        notifyItemInserted(start)
    }

}

class CombosItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleText = itemView.findViewById<TextView>(R.id.titleText)
    private val priceText = itemView.findViewById<TextView>(R.id.priceText)
    private val countText = itemView.findViewById<TextView>(R.id.countText)

    fun setData(combosBean: CombosBean) {
        imageView.loadUrl(combosBean.imgurl)
        titleText.text = combosBean.title
        priceText.text = "￥ ${combosBean.price}"
        countText.text = String.format(itemView.context.resources.getString(R.string.sellCount), combosBean.sellCount)
        itemView.setOnClickListener { ARouter.getInstance().build(RouterPath.Management.Combos_Detail).withSerializable(PARAM_COMBOS_BEAN, combosBean).navigation() }
    }


}
