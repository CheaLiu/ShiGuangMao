package com.yizhipin.usercenter.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.LoginPresenter
import com.yizhipin.usercenter.presenter.view.LoginView
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity


/**
 * Created by ${XiLei} on 2018/8/5.
 * 登录
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    /**类型(0个人,1老师,2管理人员)*/
    private var roleType: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userInfo = UserPrefsUtils.getUserInfo()
        if (userInfo != null)
            onLoginSuccess(userInfo)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mBackIv.onClick(this)
        mRegistBtn.onClick(this)
        mLoginBtn.onClick(this)
        mRightTv.onClick(this)
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPswEt) { isBtnEnable() }
        roleCheckBox.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                //员工
                roleType = 2
                mRegistBtn.visibility = GONE
            } else {
                //老师
                roleType = 1
                mRegistBtn.visibility = VISIBLE
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBackIv -> finish()
            R.id.mRegistBtn -> startActivity<RegisterActivity>()
            R.id.mRightTv -> {
                ResetPwdActivity.startActivity(this, resources.getString(R.string.forget_pwd))
            }

            R.id.mLoginBtn -> {
                val map = mutableMapOf<String, String>()
                map["mobile"] = mMobileEt.text.toString()
                map["password"] = mPswEt.text.toString()
                map["deviceToken"] = ""
                map["deviceType"] = "android"
                map["type"] = "$roleType"
                mBasePresenter.login(map)
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPswEt.text.isNullOrEmpty().not()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 登录成功
     */
    override fun onLoginSuccess(result: UserInfo) {
        Log.d("TAG", "access_token: " + result.token)
        UserPrefsUtils.putUserInfo(result)
        if (result.type == 2)
            ARouter.getInstance().build(RouterPath.Management.HOME).navigation()
        else if (result.type == 1)
            startActivity<UserInfoActivity>()
        finish()
    }

    companion object {
        fun startActivity(activity: Activity) {
            UserPrefsUtils.putUserInfo(null)
            val intent = Intent()
            intent.setClass(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

}