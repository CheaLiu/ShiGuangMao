package com.yizhipin.usercenter.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant

/**
 *本地存储用户相关信息
 */
object UserPrefsUtils {

    /**
     *  退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.id ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.mobile ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON, userInfo?.imgurl ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NICKNAME, userInfo?.nickname ?: "")
//        AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, userInfo?.push ?: false)
        AppPrefsUtils.putString(ProviderConstant.KEY_AMOUNT, userInfo?.amount ?: "0")
//        AppPrefsUtils.putString(ProviderConstant.KEY_PAY_PWD, userInfo?.payPwd ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_USER_INFO, Gson().toJson(userInfo))
    }

    fun getUserInfo(): UserInfo {
        return Gson().fromJson(AppPrefsUtils.getString(ProviderConstant.KEY_USER_INFO), object : TypeToken<UserInfo>() {}.type)
    }

    fun getUserId(): String {
        return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN)
    }
}
