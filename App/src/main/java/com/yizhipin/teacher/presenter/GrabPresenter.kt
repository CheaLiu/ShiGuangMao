package com.yizhipin.teacher.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.data.response.OrderItemBean
import com.yizhipin.teacher.view.GrabView
import com.yizhipin.usercenter.service.impl.MainServiceImpl
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/4
 * <p>抢单</p>
 */
class GrabPresenter @Inject constructor() : BasePresenter<GrabView>() {
    @Inject
    lateinit var mServiceImpl: MainServiceImpl

    fun getOrderList() {
        mServiceImpl.getOrderList().execute(object : BaseSubscriber<MutableList<OrderItemBean>>(mView) {
            override fun onNext(t: MutableList<OrderItemBean>) {
                mView.showData(t)
            }
        }, mLifecycleProvider)
    }
}