package com.qi.management.store.common_detail.presenter

import com.qi.management.store.common_detail.model.CombosDetailModelImpl
import com.qi.management.store.common_detail.view.CombosDetailView
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.mvp.presenter.BasePresenter
import java.util.*
import javax.inject.Inject

@PerComponentScope
class CombosDetailPresenterImpl
@Inject constructor(view: CombosDetailView, val mode: CombosDetailModelImpl) : CombosDetailPresenter, BasePresenter<CombosDetailView>(view) {

    private var currentBannerPosition = 0
    private lateinit var task: TimerTask


}