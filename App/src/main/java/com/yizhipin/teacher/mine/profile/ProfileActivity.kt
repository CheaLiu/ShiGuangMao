package com.yizhipin.teacher.mine.profile

import android.content.Intent
import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.profile.adapter.ProfilePagerAdapter

import com.yizhipin.teacher.mine.profile.mvp.ProfileContract
import com.yizhipin.teacher.mine.profile.mvp.ProfilePresenterImpl
import com.yizhipin.teacher.schedule.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <P>我的资料</p>
 */
class ProfileActivity : BaseMvpActivity<ProfilePresenterImpl>(), ProfileContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        titleView.setOnLeftIconClickListener { onBackPressed() }
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = ProfilePagerAdapter(arrayOf(ProfileFragment(), ProductionFragment()), supportFragmentManager)
        mTabLayout.getTabAt(0)!!.setText(R.string.tabProfile)
        mTabLayout.getTabAt(1)!!.setText(R.string.tabWork)
    }

    override fun injectComponent() {

    }


    companion object {
        fun startActivity(fragment: MeFragment) {
            val intent = Intent(fragment.context, ProfileActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}