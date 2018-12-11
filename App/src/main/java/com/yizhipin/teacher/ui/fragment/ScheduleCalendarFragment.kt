package com.yizhipin.teacher.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.ScheduleCalendarView
import com.yizhipin.teacher.presenter.ScheduleCalendarPresenter
import kotlinx.android.synthetic.main.fragment_schedule_calendar.*

/**
 * Creator Qi
 * Date 2018/12/8
 */
class ScheduleCalendarFragment : BaseMvpFragment<ScheduleCalendarPresenter>(){

    override fun injectComponent() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_calendar,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        calendarTitleView.text = String.format(resources.getString(R.string.titleCalendar),calendarView.curYear,calendarView.curMonth)
    }
}