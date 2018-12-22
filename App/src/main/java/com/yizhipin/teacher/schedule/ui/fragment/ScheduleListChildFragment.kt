package com.yizhipin.teacher.schedule.ui.fragment

import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.ScheduleListView
import com.yizhipin.teacher.dagger.component.DaggerScheduleListComponent
import com.yizhipin.teacher.dagger.module.ScheduleListModule
import com.yizhipin.teacher.schedule.presenter.ScheduleListPresenter
import kotlinx.android.synthetic.main.fragment_schedule_list_child.*

/**
 * Creator Qi
 * Date 2018/12/19
 */
class ScheduleListChildFragment : BaseMvpFragment<ScheduleListPresenter>(), ScheduleListView {

    object BundleKeys{
        const val SCHEDULE_STATUS = "SCHEDULE_STATUS"
    }
    object Status {
        const val UNFINISHED = 0
        const val FINISHED = 1
    }
    var scheduleStatus:Int = 0

    override fun injectComponent() {
        DaggerScheduleListComponent.builder().activityComponent(mActivityComponent).scheduleListModule(ScheduleListModule(this)).build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_list_child, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleStatus = arguments!![BundleKeys.SCHEDULE_STATUS] as Int
        //初始化RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val colorDrawable = ColorDrawable(resources.getColor(R.color.transparent))
        colorDrawable.bounds = Rect(0, 0, 1, resources.getDimensionPixelSize(R.dimen.common_padding))
        dividerItemDecoration.setDrawable(colorDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)
        mBasePresenter.getScheduleList(scheduleStatus)
    }

}