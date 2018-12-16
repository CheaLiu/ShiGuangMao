package com.yizhipin.teacher.schedule.model

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Creator Qi
 * Date 2018/12/12
 */
interface ScheduleService {
    /**取消预约***/
    @POST(Api.SCHEDULE_CANCEL)
    fun cancelSchedule(): Observable<BaseResp<ScheduleItemBean>>

    /**更新日程**/
    @PUT(Api.SCHEDULE_UPDATE)
    fun updateSchedule(@Path("id") id: String): Observable<BaseResp<ScheduleItemBean>>

    /**获取日程列表*/
    @GET(Api.SCHEDULE_LIST)
    fun getScheduleList(@Query("teacherId") teacherId: String): Observable<BaseResp<List<ScheduleItemBean>>>

    /**删除日程*/
    @DELETE(Api.SCHEDULE_UPDATE)
    fun deleteSchedule(@Path("id") id: String):Observable<BaseResp<Unit>>
}