package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.LoginPresenter
import com.yizhipin.usercenter.presenter.view.LoginView
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity


/**
 * Created by ${XiLei} on 2018/8/5.
 */
class RegisterActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        mSendCodeTv.onClick(this)
        mLoginBtn.onClick(this)
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mCodeEt, { isBtnEnable() })
        mLoginBtn.enable(mPswEt, { isBtnEnable() })
        mLoginBtn.enable(mInvitationCodeEt, { isBtnEnable() })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSendCodeTv -> {
//                 mBasePresenter.login(mMobileEt.text.toString(), mCodeEt.text.toString(), "user")
            }

            R.id.mLoginBtn -> {
                var map = mutableMapOf<String, String>()
                map.put("mobile", mMobileEt.text.toString())
                map.put("smsCode", mPswEt.text.toString())
                map.put("type", "user")
                mBasePresenter.login(map)
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mCodeEt.text.isNullOrEmpty().not() &&
                mPswEt.text.isNullOrEmpty().not() &&
                mInvitationCodeEt.text.isNullOrEmpty().not()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 登录成功
     */
    override fun onLoginSuccess(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
        if (result.newUser) {
            startActivity<UserInfoActivity>()
        }
        finish()
    }

}