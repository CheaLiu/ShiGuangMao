package com.qi.management.stores.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.R
import com.qi.management.stores.controler.DataFactory
import com.qi.management.stores.controler.HomeGridItem
import com.qi.management.stores.mvp.HomeGridPresenterImpl

/**
 * Creator Qi
 * Date 2019/1/27
 */
class HomeGridAdapter : RecyclerView.Adapter<HomeGridViewHolder>() {

    private val storeData: MutableList<HomeGridItem> = DataFactory.createItemList(HomeGridPresenterImpl.Style.Store)
    private val financeData: MutableList<HomeGridItem> = DataFactory.createItemList(HomeGridPresenterImpl.Style.Finance)
    private val personData: MutableList<HomeGridItem> = DataFactory.createItemList(HomeGridPresenterImpl.Style.Person)
    private val data: MutableList<HomeGridItem> = mutableListOf()
    private var style = HomeGridPresenterImpl.Style.Store

    fun setStyle(style: Int) {
        this.style = style
        data.clear()
        when (style) {
            HomeGridPresenterImpl.Style.Store -> data.addAll(storeData)
            HomeGridPresenterImpl.Style.Finance -> data.addAll(financeData)
            HomeGridPresenterImpl.Style.Person -> data.addAll(personData)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGridViewHolder {
        return HomeGridViewHolder(View.inflate(parent.context, R.layout.item_home_grid, null))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomeGridViewHolder, position: Int) {
        holder.textView.setText(data[position].name)
        val drawable = holder.textView.resources.getDrawable(data[position].drawable)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        holder.textView.setCompoundDrawables(null, drawable, null, null)
        holder.itemView.setOnClickListener {
            ARouter.getInstance().build(data[position].path).navigation()
        }
    }
}

class HomeGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.text)
}