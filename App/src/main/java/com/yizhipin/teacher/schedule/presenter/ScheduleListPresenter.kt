package com.yizhipin.teacher.schedule.presenter

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.teacher.ScheduleListView
import com.yizhipin.teacher.schedule.model.ScheduleModel
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/8
 */
class ScheduleListPresenter @Inject constructor(val model: ScheduleModel, val view: ScheduleListView) : BasePresenter<ScheduleListView>(){

    private var currentPage = 0

    fun getScheduleList(status:Int){
        var tempCurrentPage = currentPage + 1
        model.getScheduleList(UserPrefsUtils.getUserId(),status,tempCurrentPage).execute(object :BaseSubscriber<>(mView){})
    }
}