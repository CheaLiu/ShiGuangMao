package com.yizhipin.teacher.mine.setting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity

import com.yizhipin.teacher.mine.setting.mvp.SystemSettingContract
import com.yizhipin.teacher.mine.setting.mvp.SystemSettingPresenterImpl

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>系统设置</p>
 */
class SystemSettingActivity : BaseMvpActivity<SystemSettingPresenterImpl>(), SystemSettingContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_systemsetting)
    }

    override fun injectComponent() {

    }

    companion object {
        fun startActivity(fragment: Fragment) {
            val intent = Intent(fragment.context, SystemSettingActivity::class.java)
            fragment.startActivity(intent)
        }
    }

}