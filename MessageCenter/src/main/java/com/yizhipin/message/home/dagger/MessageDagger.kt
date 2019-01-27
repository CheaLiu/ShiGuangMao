package com.yizhipin.message.home.dagger

import com.yizhipin.message.home.MessageFragment
import com.yizhipin.message.home.mvp.MessageContract
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/30
 */
@PerComponentScope
@dagger.Component(modules = [Module::class], dependencies = [ActivityComponent::class])
interface Component {
    fun inject(fragment: MessageFragment)
}

@dagger.Module
class Module(val view: MessageContract.IView) {

    @Provides
    fun provideView(): MessageContract.IView {
        return view
    }
}