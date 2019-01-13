package com.yizhipin.usercenter.service.impl

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.data.repository.UserRepository
import com.yizhipin.usercenter.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var mRepository: UserRepository

    /**
     * 获取用户上班状态
     */
    override fun getUserWorkStatusList(uid: String): Observable<BaseResp<List<WorkStatusBean>>> {
        return mRepository.getWorkStatusList(uid)
    }

    override fun getCode(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.getCode(map)
                .convertBoolean()
    }

    override fun resetPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.resetPwd(map)
                .convertBoolean()
    }

    override fun register(map: MutableMap<String, String>): Observable<UserInfo> {

        return mRepository.register(map)
                .convert()
    }

    override fun login(map: MutableMap<String, String>): Observable<UserInfo> {

        return mRepository.login(map)
                .convert()
    }

    /**
     * 获取用户信息
     */
    override fun getUserInfo(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.getUserInfo(map)
                .convert()
    }

    /**
     * 编辑用户资料
     */
    override fun editUserInfo(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.editUserInfo(map)
                .convert()
    }

    override fun getCartCount(map: MutableMap<String, String>): Observable<Int> {
        return mRepository.getCartCount(map).convert()
    }

    /**
     * 绑定手机号
     */
    override fun bindMobile(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.bindMobile(map)
                .convertBoolean()
    }

    override fun setPayPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.setPayPwd(map)
                .convertBoolean()
    }

    override fun updatePayPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.updatePayPwd(map)
                .convertBoolean()
    }

    override fun resetPayPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.resetPayPwd(map)
                .convertBoolean()
    }
}