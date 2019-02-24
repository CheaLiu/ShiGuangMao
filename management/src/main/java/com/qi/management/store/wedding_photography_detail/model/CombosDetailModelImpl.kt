package com.qi.management.store.wedding_photography_detail.model

import com.qi.management.ApiService
import com.qi.management.bean.CombosBean
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.usercenter.utils.UserPrefsUtils
import io.reactivex.Observable
import javax.inject.Inject

@PerComponentScope
class CombosDetailModelImpl @Inject constructor() : CombosDetailModel {
    override fun getCombosDetail(id: String): Observable<BaseResp<CombosBean>>? {
        val userInfo = UserPrefsUtils.getUserInfo() ?: return null
        return RetrofitFactoryGet.create(ApiService::class.java).getCombosDetail(id, userInfo.id)
    }

    override fun start() {
    }

    override fun destroy() {
    }
}