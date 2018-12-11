package com.yizhipin.teacher.ui.fragment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.presenter.SchedulePresenter
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * Creator Qi
 * Date 2018/12/8
 */
class ScheduleFragment : BaseMvpFragment<SchedulePresenter>() {

    enum class ScheduleViewType {
        Calendar, List
    }

    private val scheduleCalendarFragment = ScheduleCalendarFragment()
    private val scheduleListFragment = ScheduleListFragment()

    override fun injectComponent() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mSwitchViewBtn.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.contentView,scheduleCalendarFragment, ScheduleViewType.Calendar.name)
        transaction.add(R.id.contentView,scheduleListFragment, ScheduleViewType.List.name)
        transaction.commit()
    }

}