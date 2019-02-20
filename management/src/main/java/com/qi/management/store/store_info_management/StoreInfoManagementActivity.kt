package com.qi.management.store.store_info_management

import android.content.Context
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.qi.management.R
import com.qi.management.bean.StoreBean
import com.qi.management.store.store_info_management.dagger.DaggerComponent
import com.qi.management.store.store_info_management.dagger.Module
import com.qi.management.store.store_info_management.mvp.StoreInfoManagementPresenterImpl
import com.qi.management.store.store_info_management.mvp.StoresContract
import com.qi.management.store.store_info_management.pop.IconPop
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.CityUtil
import com.yizhipin.base.utils.GlideUtils
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
        storeIconLayout.setOnClickListener(this::showIconPop)
        cityLayout.setOnClickListener {
            hideSoftInput()
            CityUtil.getInstance(this).showPickerView(this) { province, city, district -> storeCityText.text = "${province} ${city} ${district}" }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.getStore()
    }

    override fun showData(store: StoreBean) {
        Glide.with(storeIcon.context)
                .load(AppPrefsUtils.getString(BaseConstant.IMAGE_ADDRESS) + store.imgurl)
                .centerCrop()
                .override((resources.displayMetrics.density*50).toInt(), (resources.displayMetrics.density*50).toInt())
                .placeholder(com.yizhipin.base.R.drawable.default_loading)
                .error(com.yizhipin.base.R.drawable.default_loading)
                .into(object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                        storeIcon.setImageDrawable(resource)
                    }
                })
        storeNameText.setText(store.storeName)
        storeCityText.text = store.city
        storeAddressText.setText(store.detail)
        storeProfileText.text = store.content
    }

    /**
     * 更换头像
     */
    private fun showIconPop(v: View) {
        hideLoading()
        IconPop.getInstance(this).show()
    }

    override fun getImageUrl(): String {
        return ""
    }

    override fun getStoreName(): String {
        return storeNameText.text.toString()
    }

    override fun getDetailAddress(): String {
        return storeAddressText.text.toString()
    }

    override fun getStoreContent(): String {
        return storeProfileText.text.toString()
    }

    override fun getCity(): String {
        return storeCityText.text.toString()
    }

    override fun getContext(): Context {
        return this
    }

    override fun onDestroy() {
        IconPop.getInstance(this).destory()
        super.onDestroy()
    }
}