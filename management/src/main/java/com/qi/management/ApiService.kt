package com.qi.management

import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.protocol.BaseResp
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 管理层模块接口
 * Creator Qi
 * Date 2019/1/29
 */
interface ApiService {
    /**
     * 获取门店信息
     * @param id 门店id，用户信息的shopId就是门店id
     */
    @GET("api/Store/{id}")
    fun getStore(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    /**
     * 更新门店信息
     */
    @PUT("api/Store/{id}")
    fun updateStore(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    /**
     * 删除门店
     */
    @DELETE("api/Store/{id}")
    fun deleteStore(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    @PUT("api/Store/{id}")
    fun saveStoreInfo(@Path("id") id: String): Observable<BaseResp<StoreBean>>
}