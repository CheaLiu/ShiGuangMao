package com.yizhipin.base.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.PopupWindow

/**
 * Creator Qi
 * Date 2019/2/13
 */
open class BasePopWindow(val activity: Activity) : PopupWindow() {

    private var onDismissListener: OnDismissListener? = null

    init {
        width = -1
        height = -2
        this.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isOutsideTouchable = true
        isTouchable = true
        isFocusable = true
        softInputMode = PopupWindow.INPUT_METHOD_NEEDED
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        setOnDismissListener {
            val attributes = activity.window.attributes
            attributes.alpha = 1f
            activity.window.attributes = attributes
            if (this.onDismissListener != null)
                onDismissListener!!.onDismiss()
        }
    }

    fun setOnDismissListener2(onDismissListener: OnDismissListener?) {
        this.onDismissListener = onDismissListener
    }

    fun show() {
        val attributes = activity.window.attributes
        attributes.alpha = 0.5f
        activity.window.attributes = attributes
        showAtLocation(activity.window.decorView, Gravity.BOTTOM, 0, 0)
    }

    interface OnDismissListener {
        fun onDismiss()
    }
}
