package com.yizhipin.teacher.mine.chargingsetting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.mine.chargingsetting.dagger.DaggerComponent
import com.yizhipin.teacher.mine.chargingsetting.dagger.Module

import com.yizhipin.teacher.mine.chargingsetting.mvp.ChargingSettingContract
import com.yizhipin.teacher.mine.chargingsetting.mvp.ChargingSettingPresenterImpl
import kotlinx.android.synthetic.main.activity_charging_setting.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>收费设置</p>
 */
@Route(path = RouterPath.UserCenter.CHARGE_SETTING)
class ChargingSettingActivity : BaseMvpActivity<ChargingSettingPresenterImpl>(), ChargingSettingContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charging_setting)
        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }


}