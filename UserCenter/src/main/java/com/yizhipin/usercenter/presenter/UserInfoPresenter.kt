package com.yizhipin.usercenter.presenter

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    /**
     * 获取用户信息
     */
    fun getUserInfo(map1: MutableMap<String, String>) {
        mView.showLoading()
        mUserServiceImpl.getUserInfo(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN)).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.getUserResult(t)
            }
        }, mLifecycleProvider)
    }

    fun getUserInfo() {
        getUserInfo(mutableMapOf())
    }

    /**
     * 编辑用户资料
     */
    fun editUserInfo(map: MutableMap<String, String>) {
        if (!checkNetWork())
            return

        mView.showLoading()
        mUserServiceImpl.editUserInfo(map).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 获取购物车数量
     */
    fun getCartCount(map: MutableMap<String, String>) {

        mUserServiceImpl.getCartCount(map).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onGetCartSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 工作状态
     */
    fun getWorkStatusList() {
        mUserServiceImpl.getUserWorkStatusList(UserPrefsUtils.getUserId()).execute(object : CodeHandlerSubscriber<List<WorkStatusBean>>(mView) {
            override fun onSucceed(data: List<WorkStatusBean>) {
                if (data.isNotEmpty())
                    mView.showWorkStatus(data[0])
            }
        }, mLifecycleProvider)
    }

    /***上下班打卡*/
    fun postWorkStatus() {
        mUserServiceImpl.postUserWorkStatus(UserPrefsUtils.getUserId(), !UserPrefsUtils.getUserInfo().work).execute(object : CodeHandlerSubscriber<WorkStatusBean>(mView) {
            override fun onSucceed(data: WorkStatusBean) {
                mView.showWorkStatus(data)
                mView.showMsg(R.string.toastSucceedInClockingIn)
            }
        }, mLifecycleProvider)
    }

}

