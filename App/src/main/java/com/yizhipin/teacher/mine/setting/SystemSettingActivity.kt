package com.yizhipin.teacher.mine.setting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.teacher.mine.setting.mvp.SystemSettingContract
import com.yizhipin.teacher.mine.setting.mvp.SystemSettingPresenterImpl
import com.yizhipin.teacher.schedule.ui.activity.MainActivity
import com.yizhipin.teacher.schedule.ui.activity.VersionInfoActivity
import com.yizhipin.usercenter.ui.activity.ResetPwdActivity
import kotlinx.android.synthetic.main.activity_systemsetting.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>系统设置</p>
 */
class SystemSettingActivity : BaseMvpActivity<SystemSettingPresenterImpl>(), SystemSettingContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_systemsetting)
        titleView.setOnLeftIconClickListener { onBackPressed() }
        passwordBtn.setOnClickListener(this::onPasswordBtnClickListener)
        versionBtn.setOnClickListener(this::onVersionBtnClickListener)
        loginOutBtn.setOnClickListener(this::onLoginOutBtnClickListener)
        pushToggle.isChecked = AppPrefsUtils.getBoolean(ProviderConstant.KEY_IS_PUSH)
        pushToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, true)
            } else {
                AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, false)
            }
        }
    }

    override fun injectComponent() {

    }

    private fun onPasswordBtnClickListener(view: View) {
        //修改密码
        ResetPwdActivity.startActivity(view.context, resources.getString(R.string.titlePasswordModification))
    }

    private fun onVersionBtnClickListener(view: View) {
        //进入版本信息页面
        VersionInfoActivity.startActivity(view.context)
    }

    private fun onLoginOutBtnClickListener(view: View) {
        //退出登录
        MainActivity.startActivity(view.context, true)
    }

    companion object {
        fun startActivity(fragment: Fragment) {
            val intent = Intent(fragment.context, SystemSettingActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}