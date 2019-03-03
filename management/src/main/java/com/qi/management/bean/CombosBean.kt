package com.qi.management.bean

import java.io.Serializable

/**
 * 套餐bean类
 * Creator Qi
 * Date 2019/2/24
 */
data class CombosBean(
        var attention: Boolean,
        /**服装数量*/
        var clothCount: Int,
        /**详情*/
        var content: String,
        var createTime: String,
        /**评价星数*/
        var evaStar: Int,
        /**底片数量*/
        var filmCount: Int,
        /**
         * 定金(只有婚纱有定金,其他为0)
         */
        var frontMoney: Int,
        var id: Int,
        /**封面图*/
        var imgurl: String,
        /**轮播图*/
        var imgurls: String,
        /**
         * 当前登录用户ID
         */
        var loginUid: Long,
        /**原价*/
        var marketPrice: Double,
        /**
         * 拍摄类型(0景点,1大定制,2小定制)
         */
        var packagePhotoType: Int,
        /**
         * 套餐类型(0套餐,1私人定制)
         */
        var packageType: Int,
        /**价格*/
        var price: Double,
        /**入册数量*/
        var rucheCount: Int,
        /**销售量*/
        var sellCount: Int,
        /**门店ID*/
        var storeId: Int,
        /**门店图标*/
        var storeImgurl: String,
        var storeName: String,
        var tel: String,
        /**标题*/
        var title: String,
        /**
         * 类型(拍摄类型 wedding-婚纱摄影,photo-写真摄影,baby-宝宝摄影)
         */
        var type: String,
        /**是否热门*/
        var hot: Boolean,
        /**邮费*/
        var postage: Double,
        /**配送方式*/
        var sendtype: String,
        /**是否推荐*/
        var recommend: Boolean,
        /**分类ID*/
        var catagory: Int,
        /**
         * 拍价格(仅当sell_type为1是有值)
         */
        var paiAmount: Int,
        /**
         * 售卖类型(0售卖,1共享)
         */
        var sellType: Int

) : Serializable