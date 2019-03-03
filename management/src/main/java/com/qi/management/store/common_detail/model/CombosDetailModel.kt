package com.qi.management.store.common_detail.model

import com.qi.management.bean.CombosBean
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.mvp.model.BaseModel
import io.reactivex.Observable

/**
 * 套餐页详情
 * Creator Qi
 * Date 2019/2/24
 */
interface CombosDetailModel : BaseModel {
    fun getCombosDetail(id: String): Observable<BaseResp<CombosBean>>?
}