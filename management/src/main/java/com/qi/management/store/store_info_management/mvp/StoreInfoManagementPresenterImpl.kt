package com.qi.management.store.store_info_management.mvp

import android.text.TextUtils
import com.qi.management.R
import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.utils.ToastUtils
import com.yizhipin.usercenter.utils.UserPrefsUtils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 * 门店信息管理Presenter
 */
class StoreInfoManagementPresenterImpl @Inject constructor(val model: StoresModelImpl, view: StoresContract.IView) : BasePresenter<StoresContract.IView>(view), StoresContract.IPresenter {
    object Style {
        const val Store = 0
        const val Person = 1
        const val Finance = 2
    }

    var storeBean: StoreBean? = null
    fun getStore() {
        val userInfo: UserInfo? = UserPrefsUtils.getUserInfo() ?: return
        val observable: Observable<BaseResp<StoreBean>> = model.getStore(userInfo!!.shopId)
        observable.execute(object : CodeHandlerSubscriber<StoreBean>(mView) {
            override fun onSucceed(data: StoreBean) {
                storeBean = data
                mView.showData(data)
            }
        }, mLifecycleProvider)
    }

    fun saveStoreInfo() {
        if (storeBean == null) return
        storeBean!!.imgurl = mView.getImageUrl()
        storeBean!!.storeName = mView.getStoreName()
        storeBean!!.city = mView.getCity()
        storeBean!!.detail = mView.getDetailAddress()
        storeBean!!.content = mView.getStoreContent()
        storeBean!!.id = if (UserPrefsUtils.getUserInfo() == null) "" else UserPrefsUtils.getUserInfo()!!.id
        if (TextUtils.isEmpty(storeBean!!.imgurl)){
            ToastUtils.INSTANCE.showToast(mView.getContext(), R.string.errorUploadIcon)
            return
        }
    }
}