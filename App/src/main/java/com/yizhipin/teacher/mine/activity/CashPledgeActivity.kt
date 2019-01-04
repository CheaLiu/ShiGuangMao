package com.yizhipin.teacher.mine.activity

import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.dagger.CashPledgeModule
import com.yizhipin.teacher.mine.dagger.DaggerCashPledgeComponent
import com.yizhipin.teacher.mine.mvp.CashPledgePresenter
import com.yizhipin.teacher.mine.mvp.CashPledgeView

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>押金</p>
 */
class CashPledgeActivity : BaseMvpActivity<CashPledgePresenter>(), CashPledgeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_pledge)
    }

    override fun injectComponent() {
        DaggerCashPledgeComponent.builder().activityComponent(mActivityComponent).cashPledgeModule(CashPledgeModule(this)).build().inject(this)
    }
}