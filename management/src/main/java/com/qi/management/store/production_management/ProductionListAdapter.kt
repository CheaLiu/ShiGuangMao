package com.qi.management.store.production_management

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.R
import com.qi.management.bean.CombosBean
import com.qi.management.store.wedding_photography_detail.view.CommonDetailActivity
import com.qi.management.store.wedding_photography_detail.view.CommonDetailActivity.Companion.PARAM_TITLE
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.provider.router.RouterPath

/**
 * 产品列表
 * Creator Qi
 * Date 2019/2/26
 */
class ProductionListAdapter : RecyclerView.Adapter<ProductionViewHolder>() {

    private val mData: MutableList<CombosBean> = arrayListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductionViewHolder {
        val view = View.inflate(p0.context, R.layout.item_production_list, null)
        return ProductionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ProductionViewHolder, p1: Int) {
        p0.setData(mData[p1])
    }

    fun addAll(data: MutableList<CombosBean>) {
        val index = mData.size
        if (data.size > 0) {
            notifyItemInserted(index)
            mData.addAll(data)
        }
    }
}

class ProductionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleText = itemView.findViewById<TextView>(R.id.titleText)
    private val priceText = itemView.findViewById<TextView>(R.id.priceText)

    fun setData(combosBean: CombosBean) {
        imageView.loadUrl(combosBean.imgurl)
        titleText.text = combosBean.title
        priceText.text = "￥ ${combosBean.price}"
        itemView.setOnClickListener { ARouter.getInstance().build(RouterPath.Management.Combos_Detail).withSerializable(CommonDetailActivity.PARAM_COMBOS_BEAN, combosBean).withString(PARAM_TITLE, itemView.resources.getString(R.string.title_production_detail)).navigation() }
    }

}