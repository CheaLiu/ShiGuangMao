package com.yizhipin.base.rx

import com.yizhipin.base.R
import com.yizhipin.base.common.BaseApplication
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.base.utils.ToastUtils
import io.reactivex.disposables.Disposable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
abstract class CodeHandlerSubscriber<T>(private val baseView: BaseView) : BaseSubscriber<BaseResp<T>>(baseView) {
    override fun onSubscribe(d: Disposable) {
        baseView.showLoading()
    }

    override fun onNext(resp: BaseResp<T>) {
        super.onNext(resp)
        when {
            "200" == resp.code -> onSucceed(resp.data)
            "401" == resp.code -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorNetUnauthorized)
            "403" == resp.code -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorNetForbidden)
            "404" == resp.code -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorNetNotFound)
            else -> ToastUtils.INSTANCE.showToast(BaseApplication.context, resp.msg)
        }
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        } else if (e is DataNullException) {
            baseView.onDataIsNull()
        }
    }

    override fun onComplete() {
        baseView.hideLoading()
    }

    abstract fun onSucceed(data: T)
}