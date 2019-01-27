package com.qi.management.stores.controler

import com.qi.management.R
import com.qi.management.stores.mvp.HomeGridPresenterImpl
import com.yizhipin.provider.router.RouterPath

/**
 * Creator Qi
 * Date 2019/1/27
 */
class DataFactory {
    companion object {
        fun createItemList(style: Int): MutableList<HomeGridItem> {
            return when (style) {
                HomeGridPresenterImpl.Style.Store -> createStoreItems()
                HomeGridPresenterImpl.Style.Person -> createPersonItems()
                HomeGridPresenterImpl.Style.Finance -> createFinanceItems()
                else -> throw Exception("首页不存在该页面样式")
            }
        }

        private fun createFinanceItems(): MutableList<HomeGridItem> {
            val list = mutableListOf<HomeGridItem>()
            return list
        }

        private fun createPersonItems(): MutableList<HomeGridItem> {
            val list = mutableListOf<HomeGridItem>()
            return list
        }

        private fun createStoreItems(): MutableList<HomeGridItem> {
            val list = mutableListOf<HomeGridItem>()
            list.add(HomeGridItem(R.string.storeInformationManagement, R.drawable.online_store, RouterPath.Management.STORE_INFORMATION_MANAGEMENT))
            list.add(HomeGridItem(R.string.setMenuManagement, R.drawable.ic_homework, RouterPath.Management.SET_MENU_MANAGEMENT))
            list.add(HomeGridItem(R.string.storeInformationManagement, R.drawable.ic_product, RouterPath.Management.PRODUCTION_MANAGEMENT))
            list.add(HomeGridItem(R.string.storeInformationManagement, R.drawable.ic_ballgown, RouterPath.Management.CLOTHING_MANAGEMENT))
            return list
        }
    }
}