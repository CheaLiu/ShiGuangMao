package com.qi.management.store.store_info_management.pop

import android.app.Activity
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.utils.BasePopWindow

/**
 * Creator Qi
 * Date 2019/2/13
 */
class IconPop private constructor(activity: Activity) : BasePopWindow(activity) {

    init {
        contentView = View.inflate(activity, R.layout.pop_icon, null)
        contentView.layoutParams = ViewGroup.LayoutParams(-1,-2)
        val takePhotoBtn = contentView.findViewById<AppCompatTextView>(R.id.takePhotoBtn)
        val galleryBtn = contentView.findViewById<AppCompatTextView>(R.id.galleryBtn)
        val cancelBtn = contentView.findViewById<AppCompatTextView>(R.id.cancelBtn)
        takePhotoBtn.setOnClickListener { }
        galleryBtn.setOnClickListener { }
        cancelBtn.setOnClickListener { }
    }


    fun destory(){
        pop = null
    }
    companion object {
        private var pop :IconPop?=null
        fun getInstance(activity: Activity):IconPop{
            if (pop==null)
                pop = IconPop(activity)
            return pop!!
        }
    }

}