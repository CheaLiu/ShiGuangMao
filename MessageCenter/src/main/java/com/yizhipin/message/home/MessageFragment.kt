package com.yizhipin.message.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.message.R
import com.yizhipin.message.home.mvp.MessageContract
import com.yizhipin.message.home.mvp.MessagePresenterImpl

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 * 首页的消息Fragment
 */
class MessageFragment : BaseMvpFragment<MessagePresenterImpl>(), MessageContract.IView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message, null)
    }

    override fun injectComponent() {

    }


}