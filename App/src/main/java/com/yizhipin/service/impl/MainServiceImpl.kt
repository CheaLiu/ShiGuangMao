package com.yizhipin.usercenter.service.impl

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.ext.convert
import com.yizhipin.data.response.Banner
import com.yizhipin.usercenter.data.repository.MainRepository
import com.yizhipin.usercenter.service.MainService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class MainServiceImpl @Inject constructor() : MainService {

    @Inject
    lateinit var mRepository: MainRepository

    override fun getBanner(): Observable<MutableList<Banner>> {
        return mRepository.getBanner()
                .convert()
    }

    override fun getGoodsList(): Observable<MutableList<Goods>> {

        return mRepository.getGoodsList().convert()
    }

    override fun getOssAddress(): Observable<OssAddress> {

        return mRepository.getOssAddress().convert()
    }
}