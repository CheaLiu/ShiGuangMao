package com.yizhipin.teacher.mine.dagger

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.teacher.mine.mvp.CashPledgeView
import com.yizhipin.teacher.mine.activity.CashPledgeActivity
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/30
 */
@Module
class CashPledgeModule constructor(val view: CashPledgeView) {

    @Provides
    fun provideCashPledgeView(): CashPledgeView {
        return view
    }

}

@PerComponentScope
@Component(modules = [CashPledgeModule::class], dependencies = [ActivityComponent::class])
interface CashPledgeComponent {
    fun inject(activity: CashPledgeActivity)
}