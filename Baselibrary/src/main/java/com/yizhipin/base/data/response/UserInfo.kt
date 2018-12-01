package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 * 用户实体类
 */
@Parcelize
class UserInfo(val id: String,
               val nickname: String,
               val imgurl: String,
               val mobile: String,
               val password: String,
               val realName: String,
               val idCard: String,
               val cardBefore: String,
               val cardAfter: String,
               val totalAmount: String,
               val freezeAmount: String,
               val amount: String,
               val relatedUser: String,
               val type: String,
               val score: String,
               val credit: String,
               val requestCode: String,
               val registerTime: String,
               val lastLoginTime: String? = null,
               val pid: String,
               val photoAmount: String,
               val extraAmount: String,
               val teacherType: String,
               val shopId: String,
               val level: String ="0",
               val position: String,
               val openid: String,
               val qqid: String,
               val weiboid: String,
               val totalDeposit: String,
               val deposit: String,
               val deviceToken: String,
               val deviceType: String,
               val token: String? = null,
               val authCode: String? = null,
               val work: Boolean,
               val storeName: String,
               val hot: Boolean,
               val redPrompt: String
) : Parcelable