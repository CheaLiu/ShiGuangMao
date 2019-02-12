package com.qi.management.home.stores.controler

import com.qi.management.R
import com.qi.management.home.stores.controler.HomeGridItem
import com.qi.management.store.store_info_management.mvp.StoreInfoManagementPresenterImpl
import com.yizhipin.provider.router.RouterPath

/**
 * Creator Qi
 * Date 2019/1/27
 */
class DataFactory {
    companion object {
        fun createItemList(style: Int): MutableList<HomeGridItem> {
            return when (style) {
                StoreInfoManagementPresenterImpl.Style.Store -> createStoreItems()
                StoreInfoManagementPresenterImpl.Style.Person -> createPersonItems()
                StoreInfoManagementPresenterImpl.Style.Finance -> createFinanceItems()
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