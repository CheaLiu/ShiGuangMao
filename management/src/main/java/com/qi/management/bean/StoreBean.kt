package com.qi.management.bean

/**
 * Creator Qi
 * Date 2019/1/29
 */
data class StoreBean(
    val attention: Boolean,
    val city: String,
    /***门店描述*/
    val content: String,
    val createTime: String,
    /***详细地址*/
    val detail: String,
    val hot: Boolean,
    val id: Int,
    val imgurl: String,
    val lat: Int,
    val lng: Int,
    val loginUid: Int,
    val province: String,
    val redMax: Int,
    val redMin: Int,
    val serviceCount: Int,
    val starCount: Int,
    val storeName: String,
    val tel: String,
    val uid: Int
)