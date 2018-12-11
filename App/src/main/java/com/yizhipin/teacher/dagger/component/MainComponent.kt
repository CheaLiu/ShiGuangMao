package com.yizhipin.usercenter.injection.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.teacher.ui.fragment.HomeFragment
import com.yizhipin.teacher.ui.fragment.MeFragment
import com.yizhipin.usercenter.injection.module.MianModule
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = [MianModule::class])
interface MainComponent {

    fun inject(activity: HomeFragment)
    fun inject(activity: MeFragment)
}