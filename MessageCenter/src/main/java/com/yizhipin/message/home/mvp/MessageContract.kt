package com.yizhipin.message.home.mvp

import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/1/4
 */
interface MessageContract {
    interface IModel : BaseModel
    interface IPresenter
    interface IView : BaseView

}