package com.qi.management.bean

/**
 * Creator Qi
 * Date 2019/1/29
 */
class StoreBean(
        var attention: Boolean,
        var city: String,
        /***门店描述*/
        var content: String,
        var createTime: String,
        /***详细地址*/
        var detail: String,
        var hot: Boolean,
        var id: String,
        var imgurl: String,
        var lat: Double,
        var lng: Double,
        var loginUid: String,
        var province: String,
        var redMax: Int,
        var redMin: Int,
        var serviceCount: Int,
        var starCount: Int,
        var storeName: String,
        var tel: String,
        var uid: Int
)