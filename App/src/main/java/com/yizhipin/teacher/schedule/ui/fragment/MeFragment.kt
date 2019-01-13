package com.yizhipin.teacher.schedule.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yizhipin.R
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant.Companion.KEY_USER_INFO
import com.yizhipin.teacher.dagger.component.DaggerMeComponent
import com.yizhipin.teacher.mine.attention.AttentionActivity
import com.yizhipin.teacher.mine.cashpledge.DepositActivity
import com.yizhipin.teacher.mine.chargingsetting.ChargingSettingActivity
import com.yizhipin.teacher.mine.note.NoteActivity
import com.yizhipin.teacher.mine.phone.PhoneActivity
import com.yizhipin.teacher.mine.profile.ProfileActivity
import com.yizhipin.teacher.mine.setting.SystemSettingActivity
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.ui.activity.UserInfoActivity
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_me_part.*

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class MeFragment : BaseMvpFragment<UserInfoPresenter>(), UserInfoView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置Views默认样式
        initViews()
        chargeSettingLayout.setOnClickListener(this::onChargeSettingLayoutListener)//收费设置
        userIconView.setOnClickListener(this::onUserIconClickListener)//上传头像，修改基本信息
        cashPledgeLayout.setOnClickListener(this::onDepositLayoutClickListener)//我的押金
        profileLayout.setOnClickListener(this::onProfileLayoutClickListener)//我的资料、作品
        attentionLayout.setOnClickListener(this::onAttentionLayoutClickListener)//我的关注
        workNoteLayout.setOnClickListener(this::onWorkNoteLayoutClickListener)//工作须知
        phoneLayout.setOnClickListener(this::onPhoneLayoutListener)//客服电话
        settingLayout.setOnClickListener(this::onSettingLayoutListener)//系统设置
    }

    private fun initViews() {
        val userInfo = Gson().fromJson<UserInfo>(AppPrefsUtils.getString(KEY_USER_INFO), object : TypeToken<UserInfo>() {
        }.type) ?: return
        updateViews(userInfo)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBasePresenter.getWorkStatusList()
        mBasePresenter.getUserInfo()
    }

    override fun injectComponent() {
        DaggerMeComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
    }

    /**点击收费设置*/
    private fun onChargeSettingLayoutListener(view: View) {
        startActivity(Intent(context, ChargingSettingActivity::class.java))//跳转收费设置
    }

    /**修改基本信息*/
    private fun onUserIconClickListener(view: View) {
        startActivity(Intent(context, UserInfoActivity::class.java))
    }

    //押金
    private fun onDepositLayoutClickListener(view: View) {
        DepositActivity.startActivity(this)
    }

    //我的资料、作品
    private fun onProfileLayoutClickListener(view: View) {
        ProfileActivity.startActivity(this)
    }

    //我的关注
    private fun onAttentionLayoutClickListener(view: View) {
        AttentionActivity.startActivity(this)
    }

    //工作须知
    private fun onWorkNoteLayoutClickListener(view: View) {
        NoteActivity.startActivity(this)
    }

    //客服电话
    private fun onPhoneLayoutListener(view: View) {
        PhoneActivity.startActivity(this)
    }

    //系统设置
    private fun onSettingLayoutListener(view: View) {
        SystemSettingActivity.startActivity(this)
    }

    override fun getUserResult(userInfo: UserInfo) {
        UserPrefsUtils.putUserInfo(userInfo)
        updateViews(userInfo)
    }

    private fun updateViews(userInfo: UserInfo) {
        userIconView.loadUrl(userInfo.imgurl)//头像
        nickNameView.text = userInfo.nickname//昵称
        professionView.text = userInfo.position//职位
        positionView.text = userInfo.level//级别
        nameView.text = userInfo.realName//姓名
        phoneView.text = userInfo.mobile//手机号
        locationView.text = userInfo.storeName//店铺
        remainingSumView.text = userInfo.amount//余额
        creditScoreView.text = userInfo.credit//信用
        invitationCodeView.text = userInfo.requestCode//邀请码
    }

    override fun onEditUserResult(result: UserInfo) {
    }

    override fun onGetCartSuccess(result: Int) {
    }

    override fun showWorkStatus(workStatusBean: WorkStatusBean) {
        workStatusView.setText(if (workStatusBean.work) R.string.onDuty else R.string.offDuty)
    }
}