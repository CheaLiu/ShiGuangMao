package com.yizhipin.teacher.mine.paymentdeposit.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class PaymentDepositPresenterImpl @Inject constructor(val model: PaymentDepositModelImpl, view: PaymentDepositContract.IView) : BasePresenter<PaymentDepositContract.IView>(view), PaymentDepositContract.IPresenter {

}