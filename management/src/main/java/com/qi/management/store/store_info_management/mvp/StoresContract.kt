package com.qi.management.store.store_info_management.mvp

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
    }

}