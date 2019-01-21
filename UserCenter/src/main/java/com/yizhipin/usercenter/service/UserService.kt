package com.yizhipin.usercenter.service

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.usercenter.bean.WorkStatusBean
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface UserService {

    fun getCode(map: MutableMap<String, String>): Observable<Boolean>
    fun resetPwd(map: MutableMap<String, String>): Observable<Boolean>
    fun register(map: MutableMap<String, String>): Observable<UserInfo>
    fun login(map: MutableMap<String, String>): Observable<UserInfo>
    fun getUserInfo(uid: String): Observable<UserInfo>
    fun editUserInfo(map: MutableMap<String, String>): Observable<UserInfo>
    fun bindMobile(map: MutableMap<String, String>): Observable<Boolean>
    fun getCartCount(map: MutableMap<String, String>): Observable<Int>
    fun setPayPwd(map: MutableMap<String, String>): Observable<Boolean>
    fun updatePayPwd(map: MutableMap<String, String>): Observable<Boolean>
    fun resetPayPwd(map: MutableMap<String, String>): Observable<Boolean>
    fun getUserWorkStatusList(uid: String): Observable<BaseResp<List<WorkStatusBean>>>
    fun postUserWorkStatus(uid: String, workStatus: Boolean): Observable<BaseResp<WorkStatusBean>>
}
