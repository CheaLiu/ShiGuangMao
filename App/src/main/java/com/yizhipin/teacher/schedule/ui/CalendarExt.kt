package com.yizhipin.teacher.schedule.ui

import android.view.View
import com.haibin.calendarview.CalendarView
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.view.CustomMonthView

/**
 * Creator Qi
 * Date 2018/12/15
 */
fun CalendarView.update(scheduleMap: Map<String, ScheduleItemBean>) {
    mDelegate.scheduleMap = scheduleMap
    val view = monthViewPager.findViewById<View>(monthViewPager.currentItem)
    view?.invalidate()
}