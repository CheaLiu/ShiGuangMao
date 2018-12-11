package com.yizhipin.teacher.presenter

import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.teacher.ScheduleCalendarView

/**
 * Creator Qi
 * Date 2018/12/8
 * <p>排期日历展示</p>
 */
class ScheduleCalendarPresenter : BasePresenter<ScheduleCalendarView>() {

    companion object {
        private val ScheduleMap: HashMap<String, ScheduleItemBean> = HashMap()

        fun getScheduleByDate(date: String): ScheduleItemBean? {
            return ScheduleMap[date]
        }

        fun addSchedule(schedule: ScheduleItemBean) {
            ScheduleMap[schedule.scheduleDate] = schedule
        }

        fun removeSchedule(schedule: ScheduleItemBean) {
            ScheduleMap.remove(schedule.scheduleDate)
        }

        fun addScheduleList(scheduleList: List<ScheduleItemBean>, onAddOverListener: () -> Unit) {
            scheduleList.forEach {
                ScheduleMap[it.scheduleDate.split("T")[0]] = it
            }
            onAddOverListener()
        }
    }

}