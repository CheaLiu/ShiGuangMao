package com.yizhipin.teacher.schedule.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant.Companion.KEY_SP_TOKEN
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.teacher.ScheduleCalendarView
import com.yizhipin.teacher.dagger.component.DaggerScheduleComponent
import com.yizhipin.teacher.dagger.module.ScheduleModule
import com.yizhipin.teacher.schedule.presenter.ScheduleCalendarPresenter
import com.yizhipin.teacher.schedule.ui.update
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.fragment_schedule_calendar.*

/**
 * Creator Qi
 * Date 2018/12/8
 */
class ScheduleCalendarFragment : BaseMvpFragment<ScheduleCalendarPresenter>(), ScheduleCalendarView {

    override fun injectComponent() {
        DaggerScheduleComponent.builder()
                .activityComponent(mActivityComponent)
                .scheduleModule(ScheduleModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        calendarTitleView.text = String.format(resources.getString(R.string.titleCalendar), calendarView.curYear, calendarView.curMonth)
        //日历选择监听器
        calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
            override fun onCalendarOutOfRange(calendar: Calendar?) {
            }

            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                if (calendar == null) return
                if (!isClick) return
                val date = (calendar.year.toString() + "-"
                        + (if (calendar.month < 10) 0.toString() + "" + calendar.month else calendar.month) + "-"
                        + if (calendar.day < 10) 0.toString() + "" + calendar.day else calendar.day)
                val itemBean = mBasePresenter.getScheduleByDate(date)
                if (itemBean == null) {
                    //不接受预约
                    mBasePresenter.cancelSchedule(date, AppPrefsUtils.getString(KEY_SP_TOKEN))
                } else if (TextUtils.isEmpty(itemBean.nickname) && "2" == itemBean.status) {
                    //日程为休息状态且不存在服务用户，那么删除日程
                    mBasePresenter.deleteScheduleFromNet(itemBean)
                }
            }
        })
        //获取老师日程列表
        mBasePresenter.getScheduleListFromNet(UserPrefsUtils.getUserId())
    }

    override fun uploadSchedule(scheduleMap: HashMap<String, ScheduleItemBean>) {
        calendarView.update(scheduleMap)
    }
}


