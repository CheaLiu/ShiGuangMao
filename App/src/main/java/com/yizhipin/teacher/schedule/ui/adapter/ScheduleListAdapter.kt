package com.yizhipin.teacher.schedule.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.data.response.ScheduleItemBean

/**
 * Creator Qi
 * Date 2018/12/19
 */
class ScheduleListAdapter : RecyclerView.Adapter<ViewHolder>() {

    val data = ArrayList<ScheduleItemBean>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = View.inflate(p0.context, R.layout.item_schedule_list, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}