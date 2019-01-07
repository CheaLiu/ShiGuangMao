package com.yizhipin.teacher.schedule.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.mine.baseinfo.BaseInfoActivity
import com.yizhipin.teacher.mine.cashpledge.DepositActivity
import com.yizhipin.teacher.mine.chargingsetting.ChargingSettingActivity
import com.yizhipin.usercenter.injection.component.DaggerMainComponent
import com.yizhipin.usercenter.injection.module.MianModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_me_part.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class MeFragment : BaseMvpFragment<UserInfoPresenter>() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chargeSettingLayout.setOnClickListener(this::onChargeSettingLayoutListener)//收费设置
        userIconView.setOnClickListener(this::onUserIconClickListener)//上传头像，修改基本信息
        cashPledgeLayout.setOnClickListener(this::onDepositLayoutClickListener)//我的押金
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mianModule(MianModule()).build().inject(this)
    }

    override fun onStart() {
        super.onStart()
    }

    /**点击收费设置*/
    private fun onChargeSettingLayoutListener(view: View) {
        startActivity(Intent(context, ChargingSettingActivity::class.java))//跳转收费设置
    }

    /**修改基本信息*/
    private fun onUserIconClickListener(view: View) {
        startActivity(Intent(context, BaseInfoActivity::class.java))
    }

    //押金
    private fun onDepositLayoutClickListener(view: View) {
        DepositActivity.startActivity(this)
    }

}