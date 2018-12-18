package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.presenter.AuthenticationPresenter
import com.yizhipin.usercenter.presenter.view.AuthenticationView

/**
 * Creator Qi
 * Date 2018/12/18
 */
class AuthenticationActivity : BaseMvpActivity<AuthenticationPresenter>(), AuthenticationView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }

    override fun injectComponent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(mes: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}