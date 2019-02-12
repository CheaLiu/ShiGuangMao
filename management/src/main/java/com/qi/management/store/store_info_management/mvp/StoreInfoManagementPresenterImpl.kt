package com.qi.management.store.store_info_management.mvp

import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject
import com.yizhipin.base.ext.execute
import io.reactivex.Observable

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 */
class StoreInfoManagementPresenterImpl @Inject constructor(val model: StoresModelImpl, view: StoresContract.IView) : BasePresenter<StoresContract.IView>(view), StoresContract.IPresenter {
    object Style {
        const val Store = 0
        const val Person = 1
        const val Finance = 2
    }

    fun getStore() {
        val userInfo: UserInfo? = UserPrefsUtils.getUserInfo() ?: return
        val observable: Observable<BaseResp<StoreBean>> = model.getStore(userInfo!!.shopId)
        observable.execute(object : CodeHandlerSubscriber<StoreBean>(mView) {
            override fun onSucceed(data: StoreBean) {
                mView.showData(data)
            }
        }, mLifecycleProvider)
    }
}