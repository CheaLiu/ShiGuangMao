package com.yizhipin.usercenter.service

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.data.response.Banner
import com.yizhipin.data.response.ScheduleItemBean
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface MainService {

    fun getBanner(): Observable<MutableList<Banner>>
    fun getGoodsList(): Observable<MutableList<Goods>>
    fun getOssAddress(): Observable<OssAddress>
    fun getOrderList():Observable<MutableList<ScheduleItemBean>>
}
