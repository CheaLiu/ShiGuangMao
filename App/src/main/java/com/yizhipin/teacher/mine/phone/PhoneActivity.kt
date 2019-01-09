package com.yizhipin.teacher.mine.phone

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity

import com.yizhipin.teacher.mine.phone.mvp.PhoneContract
import com.yizhipin.teacher.mine.phone.mvp.PhonePresenterImpl
import kotlinx.android.synthetic.main.activity_phone.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>客服电话</p>
 */
class PhoneActivity : BaseMvpActivity<PhonePresenterImpl>(), PhoneContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)
        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun injectComponent() {

    }


    companion object {
        fun startActivity(fragment: Fragment) {
            val intent = Intent(fragment.context, PhoneActivity::class.java)
            fragment.startActivity(intent)
        }
    }

}