package com.yizhipin.message.home.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class MessagePresenterImpl @Inject constructor(val model: MessageModelImpl, view: MessageContract.IView) : BasePresenter<MessageContract.IView>(view), MessageContract.IPresenter {

}