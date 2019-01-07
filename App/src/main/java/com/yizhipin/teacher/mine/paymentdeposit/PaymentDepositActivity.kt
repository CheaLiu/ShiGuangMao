package com.yizhipin.teacher.mine.paymentdeposit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.paymentdeposit.dagger.DaggerComponent
import com.yizhipin.teacher.mine.paymentdeposit.dagger.Module
import com.yizhipin.teacher.mine.paymentdeposit.mvp.PaymentDepositContract
import com.yizhipin.teacher.mine.paymentdeposit.mvp.PaymentDepositPresenterImpl

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>支付押金</p>
 */
class PaymentDepositActivity : BaseMvpActivity<PaymentDepositPresenterImpl>(), PaymentDepositContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymentdeposit)
    }

    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, PaymentDepositActivity::class.java)
            activity.startActivity(intent)
        }
    }
}