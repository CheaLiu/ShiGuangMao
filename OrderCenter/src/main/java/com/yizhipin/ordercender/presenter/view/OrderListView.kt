package com.yizhipin.ordercender.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.ordercender.data.response.Order

/*
    订单列表 视图回调
 */
interface OrderListView : BaseView {

    //获取订单列表回调
    fun onGetOrderListResult(result: BasePagingResp<MutableList<Order>>)
    //确认订单回调
    fun onConfirmOrderResult(result:Boolean)
    //取消订单回调
    fun onCancelOrderResult(result:Boolean)

}
