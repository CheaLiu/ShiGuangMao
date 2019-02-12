package com.qi.management.store.store_info_management

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.qi.management.R
import com.qi.management.bean.StoreBean
import com.qi.management.store.store_info_management.dagger.DaggerComponent
import com.qi.management.store.store_info_management.dagger.Module
import com.qi.management.store.store_info_management.mvp.StoreInfoManagementPresenterImpl
import com.qi.management.store.store_info_management.mvp.StoresContract
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_store_info_management.*

/**
 * 门店信息管理
 * Creator Qi
 * Date 2019/1/28
 */
@Route(path = RouterPath.Management.STORE_INFORMATION_MANAGEMENT)
class StoreInfoManagementActivity : BaseMvpActivity<StoreInfoManagementPresenterImpl>(), StoresContract.IView {

    override fun onCreateView(): Int {
        return R.layout.activity_store_info_management
    }

    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.getStore()
    }

    override fun showData(store: StoreBean) {
        storeIcon.loadUrl(store.imgurl)
        storeNameText.text = store.storeName
        storeCityText.text = "${store.province}.${store.city}"
        storeAddressText.text = store.detail
        storeProfileText.text = store.content
    }
}