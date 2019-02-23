package com.qi.management.store.store_info_management.pop

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.utils.BasePopWindow
import com.yizhipin.base.utils.TakePhotoUtils

/**
 * Creator Qi
 * Date 2019/2/13
 */
class IconPop constructor(activity: Activity, onTakePhotoResult: TakePhotoUtils.OnTakePhotoResult) : BasePopWindow(activity) {

    private var takePhotoUtils: TakePhotoUtils? = null

    init {
        contentView = View.inflate(activity, R.layout.pop_icon, null)
        contentView.layoutParams = ViewGroup.LayoutParams(-1, -2)
        val takePhotoBtn = contentView.findViewById<AppCompatTextView>(R.id.takePhotoBtn)
        val galleryBtn = contentView.findViewById<AppCompatTextView>(R.id.galleryBtn)
        val cancelBtn = contentView.findViewById<AppCompatTextView>(R.id.cancelBtn)
        takePhotoBtn.setOnClickListener {
            takePhotoUtils?.takePhoto()
            dismiss()
        }
        galleryBtn.setOnClickListener {
            takePhotoUtils?.pickPhoto(6)
            dismiss()
        }
        cancelBtn.setOnClickListener { dismiss() }
        takePhotoUtils = TakePhotoUtils(activity, null)
        takePhotoUtils?.onTakePhotoResult = onTakePhotoResult
    }

    fun destroy() {
        if (activity != null) activity = null
        if (takePhotoUtils != null) {
            takePhotoUtils!!.destroy()
            takePhotoUtils = null
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (takePhotoUtils != null)
            takePhotoUtils!!.onActivityResult(requestCode, resultCode, data)
    }
}