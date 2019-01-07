package com.yizhipin.teacher.mine.cashpledge

import android.content.Intent
import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.cashpledge.dagger.CashPledgeModule
import com.yizhipin.teacher.mine.cashpledge.dagger.DaggerCashPledgeComponent
import com.yizhipin.teacher.mine.cashpledge.mvp.CashPledgePresenter
import com.yizhipin.teacher.mine.cashpledge.mvp.CashPledgeView
import com.yizhipin.teacher.mine.depositrefund.DepositRefundActivity
import com.yizhipin.teacher.mine.paymentdeposit.PaymentDepositActivity
import com.yizhipin.teacher.schedule.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_deposit.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>押金</p>
 */
class DepositActivity : BaseMvpActivity<CashPledgePresenter>(), CashPledgeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        titleView.setOnLeftIconClickListener { onBackPressed() }
        rechargeDepositBtn.setOnClickListener { PaymentDepositActivity.startActivity(this) }
        depositRefundBtn.setOnClickListener { DepositRefundActivity.startActivity(this) }
    }

    override fun injectComponent() {
        DaggerCashPledgeComponent.builder().activityComponent(mActivityComponent).cashPledgeModule(CashPledgeModule(this)).build().inject(this)
    }

    companion object {
        fun startActivity(fragment: MeFragment) {
            val intent = Intent(fragment.context, DepositActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}