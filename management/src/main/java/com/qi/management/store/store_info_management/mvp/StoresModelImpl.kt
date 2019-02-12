package com.qi.management.store.store_info_management.mvp

import com.qi.management.ApiService
import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BaseResp
import io.reactivex.Observable
import javax.inject.Inject

/**
 * 门店信息
 * Creator Qi
 * Date 2018/12/30
 */
class StoresModelImpl @Inject constructor() : StoresContract.IModel {

    override fun start() {

    }

    override fun destroy() {

    }

    fun getStore(shopID: String): Observable<BaseResp<StoreBean>> {
        return RetrofitFactoryGet.create(ApiService::class.java).getStore(shopID)
    }
}