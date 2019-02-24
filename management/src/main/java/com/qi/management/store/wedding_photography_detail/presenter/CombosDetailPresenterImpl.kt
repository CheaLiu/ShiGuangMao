package com.qi.management.store.wedding_photography_detail.presenter

import com.qi.management.store.wedding_photography_detail.model.CombosDetailModelImpl
import com.qi.management.store.wedding_photography_detail.view.CombosDetailView
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

@PerComponentScope
class CombosDetailPresenterImpl
@Inject constructor(view: CombosDetailView, val mode: CombosDetailModelImpl) : CombosDetailPresenter, BasePresenter<CombosDetailView>(view) {

}