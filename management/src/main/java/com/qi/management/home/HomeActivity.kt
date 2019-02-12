package com.qi.management.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.qi.management.R
import com.qi.management.home.stores.HomeGridFragment
import com.qi.management.store.store_info_management.mvp.StoreInfoManagementPresenterImpl
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.message.home.MessageFragment
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.common.IntentParams.EXIST
import com.yizhipin.usercenter.me.fragment.MeFragment
import com.yizhipin.usercenter.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*

@Route(path = RouterPath.Management.HOME)
class HomeActivity : BaseActivity() {
    private val storesFragment by lazy { HomeGridFragment() }
    private val messageFragment by lazy { MessageFragment() }
    private val meFragment by lazy { MeFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initFragment()
        initBottomNav()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.hasExtra(EXIST)) {//判断其他Activity启动本Activity时传递来的intent是否为空
            val isExist = intent.getBooleanExtra(EXIST, false)
            if (isExist) {
                //如果为真则退出本Activity,跳转到登录页
                LoginActivity.startActivity(this)
//                this.finish()
            }
        }
    }

    private fun initBottomNav() {
        //门店
        val storeItem = BottomNavigationItem(
                R.drawable.ic_nav_bottom_store_checked,
                resources.getString(R.string.navStore))
                .setInactiveIconResource(R.drawable.ic_nav_bottom_store_checked)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)
        //人员
        val personItem = BottomNavigationItem(R.drawable.ic_nav_bottom_person, resources.getString(R.string.navPerson))
                .setInactiveIconResource(R.drawable.ic_nav_bottom_person)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)

        //消息
        val categoryItem = BottomNavigationItem(
                R.drawable.ic_msg_checked,
                resources.getString(R.string.navMsg))
                .setInactiveIconResource(R.drawable.ic_msg_unchecked)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)
        //财务
        val financeItem = BottomNavigationItem(R.drawable.ic_nav_bottom_finance, resources.getString(R.string.navPerson))
                .setInactiveIconResource(R.drawable.ic_nav_bottom_finance)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)
        //我的
        val userItem = BottomNavigationItem(
                R.drawable.ic_mine_checked,
                resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.ic_mine_unchecked)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)
        //设置Nav样式
        mBottomNavBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(storeItem)
                .addItem(personItem)
                .addItem(categoryItem)
                .addItem(financeItem)
                .addItem(userItem)
                .setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                    override fun onTabReselected(position: Int) {

                    }

                    override fun onTabUnselected(position: Int) {

                    }

                    override fun onTabSelected(position: Int) {
                        checkItem(position)
                    }

                })
                .setFirstSelectedPosition(0)
                .initialise()
    }

    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mContainer, storesFragment)
        transaction.add(R.id.mContainer, messageFragment)
        transaction.add(R.id.mContainer, meFragment)
        transaction.show(storesFragment)
        transaction.hide(messageFragment)
        transaction.hide(meFragment)
        transaction.commit()
    }

    /**
     * 选择NavigationItem
     */
    private fun checkItem(index: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (index == 0 || index == 1 || index == 3) {
            beginTransaction.show(storesFragment)
            beginTransaction.hide(messageFragment)
            beginTransaction.hide(meFragment)
        } else if (index == 2) {
            beginTransaction.show(messageFragment)
            beginTransaction.hide(storesFragment)
            beginTransaction.hide(meFragment)
        } else if (index == 4) {
            beginTransaction.show(meFragment)
            beginTransaction.hide(storesFragment)
            beginTransaction.hide(messageFragment)
        }
        beginTransaction.commit()
        when (index) {
            0 -> storesFragment.changeStyle(StoreInfoManagementPresenterImpl.Style.Store)
            1 -> storesFragment.changeStyle(StoreInfoManagementPresenterImpl.Style.Person)
            3 -> storesFragment.changeStyle(StoreInfoManagementPresenterImpl.Style.Finance)
        }
    }

    companion object {
        fun startActivity(context: Context, isExistsLogin: Boolean) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(EXIST, isExistsLogin)
            context.startActivity(intent)
        }
    }
}
