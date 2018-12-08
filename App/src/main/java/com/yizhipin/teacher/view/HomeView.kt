package com.yizhipin.teacher.view

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.data.response.Banner
import com.yizhipin.data.response.OrderItemBean

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface HomeView : BaseView {
    fun onGetBannerSuccess(result: MutableList<Banner>)
    fun onGetGoodsListSuccess(result: MutableList<Goods>)
    fun onGetOssAddressSuccess(result: OssAddress)
}

/**
 * 抢单
 */
interface GrabView : BaseView {
    fun showData(data: MutableList<OrderItemBean>)
}