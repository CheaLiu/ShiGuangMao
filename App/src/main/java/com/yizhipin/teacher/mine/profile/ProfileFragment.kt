package com.yizhipin.teacher.mine.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R

/**
 * Creator Qi
 * Date 2019/1/8
 * <p>我的资料</p>
 */
class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_profile, null)
    }
}