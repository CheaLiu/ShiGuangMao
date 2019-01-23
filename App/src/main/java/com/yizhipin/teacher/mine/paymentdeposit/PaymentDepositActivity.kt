package com.yizhipin.teacher.mine.paymentdeposit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.paymentdeposit.constant.PayType
import com.yizhipin.teacher.mine.paymentdeposit.dagger.DaggerComponent
import com.yizhipin.teacher.mine.paymentdeposit.dagger.Module
import com.yizhipin.teacher.mine.paymentdeposit.mvp.PaymentDepositContract
import com.yizhipin.teacher.mine.paymentdeposit.mvp.PaymentDepositPresenterImpl
import kotlinx.android.synthetic.main.activity_paymentdeposit.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>支付押金</p>
 */
class PaymentDepositActivity : BaseMvpActivity<PaymentDepositPresenterImpl>(), PaymentDepositContract.IView {
    private lateinit var payMethodViewList: Array<CheckBox>

    override fun onCreateView(): Int {
        return R.layout.activity_paymentdeposit
    }


    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
        titleView.setOnLeftIconClickListener { onBackPressed() }
        decrementBtn.setOnClickListener { mBasePresenter.decrement() }
        increaseBtn.setOnClickListener { mBasePresenter.increase() }
        confirmPaymentBtn.setOnClickListener { mBasePresenter.recharge(getPayType()) }
        balancePaymentCheckBox.tag = PayType.yue
        alipayCheckBox.tag = PayType.Alipay
        weChatPayCheckBox.tag = PayType.Weixin
        payMethodViewList = arrayOf(balancePaymentCheckBox, alipayCheckBox, weChatPayCheckBox)
        for (checkBox in payMethodViewList) {
            checkBox.setOnCheckedChangeListener(this::onCheckedListener)
        }
        payMethodViewList[0].isChecked = true
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.initData()
        updateAmount(mBasePresenter.amount)
    }

    override fun updateAmount(amount: Double) {
        amountText.text = "$amount"
        moneyRMBText.text = resources.getString(R.string.moneyRMB, amount)
    }

    private fun onCheckedListener(button: CompoundButton, checked: Boolean) {
        if (checked) {
            for (checkBox in payMethodViewList) {
                if (checkBox != button)
                    checkBox.isChecked = false
            }
        }
    }

    private fun getPayType(): String {
        for (checkBox in payMethodViewList) {
            if (checkBox.isChecked) {
                return checkBox.tag.toString()
            }
        }
        return ""
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, PaymentDepositActivity::class.java)
            activity.startActivity(intent)
        }
    }
}