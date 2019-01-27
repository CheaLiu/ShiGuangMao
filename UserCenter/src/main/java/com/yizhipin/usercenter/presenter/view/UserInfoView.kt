package com.yizhipin.usercenter.presenter.view

import android.app.Activity
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.usercenter.bean.WorkStatusBean

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface UserInfoView : BaseView {
    fun getUserResult(result: UserInfo)
    fun onEditUserResult(result: UserInfo)
    fun onGetCartSuccess(result: Int)
    fun showWorkStatus(workStatusBean: WorkStatusBean)
    fun getActivity(): Activity?
}