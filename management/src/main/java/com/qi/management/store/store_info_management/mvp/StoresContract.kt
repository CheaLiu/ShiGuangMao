package com.qi.management.store.store_info_management.mvp

import android.content.Context
import com.qi.management.bean.StoreBean
import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/1/4
 */
interface StoresContract {
    interface IModel : BaseModel
    interface IPresenter
    interface IView : BaseView {
        fun showData(store: StoreBean)
        fun getImageUrl(): String
        /**
         * 门店名称
         */
        fun getStoreName(): String

        /**
         * 详细地址
         */
        fun getDetailAddress(): String

        /**
         * 门店介绍
         */
        fun getStoreContent(): String

        /**
         * 获取城市
         */
        fun getCity(): String

        fun getContext(): Context
    }

}