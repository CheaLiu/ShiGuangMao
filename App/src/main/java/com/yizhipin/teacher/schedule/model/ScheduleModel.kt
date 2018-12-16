package com.yizhipin.teacher.schedule.model

import com.yizhipin.base.data.net.RetrofitFactoryDelete
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.net.RetrofitFactoryPut
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.data.response.ScheduleItemBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/8
 */
class ScheduleModel @Inject constructor() : BaseModel() {

    override fun start() {
    }

    override fun destory() {
    }

    /**
     * 不接受预约
     */
    fun cancelSchedule(scheduleDate: String, teacherId: String): Observable<BaseResp<ScheduleItemBean>> {
        val map = HashMap<String, String>()
        map["teacherId"] = teacherId
        map["scheduleDate"] = scheduleDate
        return RetrofitFactoryPost(map).create(ScheduleService::class.java).cancelSchedule()
    }

    /**
     * 更新日程
     */
    fun updateSchedule(itemBean: ScheduleItemBean): Observable<BaseResp<ScheduleItemBean>> {
        val map = HashMap<String, String>()
        map["status"] = itemBean.status
//        map["amount"] = itemBean.amount
//        map["dateMoth"] = itemBean.dateMonth
//        map["teacherId"] = itemBean.teacherId
//        map["scheduleDate"] = itemBean.scheduleDate
        return RetrofitFactoryPut(map).create(ScheduleService::class.java).updateSchedule(itemBean.id)
    }

    fun getScheduleListFromNet(teacherId: String): Observable<BaseResp<List<ScheduleItemBean>>> {
        return RetrofitFactoryGet.create(ScheduleService::class.java).getScheduleList(teacherId)
    }

    fun deleteSchedule(id: String): Observable<BaseResp<Unit>> {
        val map = HashMap<String,String>()
        map["id"] = id
        return RetrofitFactoryDelete(map).create(ScheduleService::class.java).deleteSchedule(id)
    }
}