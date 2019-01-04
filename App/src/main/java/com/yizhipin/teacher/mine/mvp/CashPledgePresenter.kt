package com.yizhipin.teacher.mine.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class CashPledgePresenter @Inject constructor(val model : CashPledgeModel,view: CashPledgeView) : BasePresenter<CashPledgeView>(view)