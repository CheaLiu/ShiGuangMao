package com.qi.management.store.wedding_photography.comboslist.view

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.qi.management.R
import com.qi.management.store.wedding_photography.comboslist.adapter.CombosPagerAdapter
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_combos_list.*

/**
 * 选择套餐页面
 */
@Route(path = RouterPath.Management.SET_MENU_MANAGEMENT)
class CombosListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combos_list)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {
        titleView.setOnLeftIconClickListener { onBackPressed() }
        addCombosBtn.setOnClickListener { }

        tabLayout.addTab(tabLayout.newTab().setText(R.string.navAll))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.personalTailor))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.combos))

        val fragments = arrayListOf<CombosPagerAdapter.CombosPagerItem>()
        fragments.add(CombosPagerAdapter.CombosPagerItem(CombosListFragment().setCombosType(CombosListFragment.ALL), resources.getString(R.string.navAll)))
        fragments.add(CombosPagerAdapter.CombosPagerItem(CombosListFragment().setCombosType(CombosListFragment.PERSONAL), resources.getString(R.string.personalTailor)))
        fragments.add(CombosPagerAdapter.CombosPagerItem(CombosListFragment().setCombosType(CombosListFragment.COMBOS), resources.getString(R.string.combos)))
        viewPager.adapter = CombosPagerAdapter(supportFragmentManager, fragments)
        tabLayout.setupWithViewPager(viewPager)
    }


}
