package com.yizhipin.provider.router

/*
    模块路由 路径定义
 */
object RouterPath {
    //用户模块
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
            const val SET_PAY_PWD = "/userCenter/setPayPwd" //设置支付密码
            const val UPDATE_PAY_PWD = "/userCenter/updatePayPwd" //修改支付密码
            const val RESET_PAY_PWD = "/userCenter/resetPayPwd" //重置支付密码
            /**收费设置*/
            const val CHARGE_SETTING = "/home/me/charge_setting"
            /**押金*/
            const val DEPOSIT = "/home/me/deposit"
            /**个人资料*/
            const val PROFILE = "/home/me/profile"
            /**我的关注*/
            const val ATTENTION = "/home/me/attention"
            /**新手帮助*/
            const val NOTE = "/home/me/note"
            /**客服电话*/
            const val PHONE = "/home/me/phone"
            /**系统设置*/
            const val SYSTEM_SETTING = "/home/me/system_setting"
        }
    }

    //商品模块
    class GoodsCenter {
        companion object {
            const val PATH_GOODS_CART = "/goodsCenter/cart"
            const val PATH_GOODS_COLLECT = "/goodsCenter/collect"
        }
    }

    //订单模块
    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
            const val PATH_ORDER_DETAILS = "/orderCenter/details"
            const val PATH_ORDER_COUPON = "/orderCenter/coupon" //优惠券
        }
    }

    //支付模块
    class PayCenter {
        companion object {
            const val PATH_PAY_RECHARGE = "/payCenter/recharge"
            const val PATH_PAY_WITHDRAW = "/payCenter/withraw"
        }
    }

    //消息模块
    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/message/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }

    object Management {
        const val HOME: String = "/management/home"
        /**门店信息管理*/
        const val STORE_INFORMATION_MANAGEMENT = "/management/store/information_management"
        /**套餐列表页*/
        const val PACKAGE_LIST: String = "/management/store/package_list"
        /**详情*/
        const val Detail: String = "/management/store/combos/detail"
        /**产品管理*/
        const val PRODUCTION_LIST: String = "/management/store/production_management"
        /**服装馆页面*/
        const val COSTUME_PAVILION: String = "/management/store/costume_pavilion"
        /**服装列表*/
        const val COSTUME_LIST: String = "/management/store/costume_list"
    }
}
