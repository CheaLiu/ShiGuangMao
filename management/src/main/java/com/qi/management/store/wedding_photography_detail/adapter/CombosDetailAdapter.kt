package com.qi.management.store.wedding_photography_detail.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.qi.management.bean.CombosBean
import com.qi.management.store.wedding_photography_detail.holder.CombosDetailHolder

/**
 * Creator Qi
 * Date 2019/2/24
 */
class CombosDetailAdapter(val combosBean: CombosBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ItemType(val value: Int) {
        Banner(0), Images(1)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return when (p1) {
            ItemType.Banner.value -> CombosDetailHolder(View.inflate(p0.context, R.layout.item_combos_detail_banner_holder, null))
            else -> CombosDetailHolder(View.inflate(p0.context, R.layout.item_combos_detail_banner_holder, null))
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ItemType.Banner.value
            else -> ItemType.Images.value
        }
    }

}