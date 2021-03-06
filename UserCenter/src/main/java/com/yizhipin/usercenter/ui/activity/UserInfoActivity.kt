package com.yizhipin.usercenter.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.UploadUtil
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.devio.takephoto.model.TResult
import org.jetbrains.anko.toast
import java.io.File

/**
 * Created by ${XiLei} on 2018/7/26.
 * 完善资料
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener, UploadUtil.OnUploadProcessListener {

    override fun getActivity(): Activity? {
        return this
    }

    override fun showWorkStatus(workStatusBean: WorkStatusBean) {
    }

    private var mRemoteFileUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
    }

    private fun initView() {

        mBackIv.onClick(this)
        mUserIconView.onClick(this)
        mConfirmBtn.onClick(this)
        mRightTv.onClick(this)
        mConfirmBtn.enable(mNickEt, { isBtnEnable() })
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        var map = mutableMapOf<String, String>()
        map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        mPresenter.getUserInfo(map)
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.mRightTv, R.id.mBackIv -> finish()

            R.id.mUserIconView -> showAlertView()

            R.id.mConfirmBtn -> {

                var map = mutableMapOf<String, String>()
                map.put("nickname", mNickEt.text.toString())
                map.put("imgurl", mRemoteFileUrl)
                mPresenter.editUserInfo(map)
            }

        }
    }

    /**
     * 获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {
        if (!mPresenter.checkNetWork()) {
            return
        }
        val localFileUrl = result?.image?.compressPath

        val fileKey = "avatarFile"
        val uploadUtil = UploadUtil.getInstance()
        uploadUtil.setOnUploadProcessListener(this@UserInfoActivity) //设置监听器监听上传状态

        showLoading()
        val filepath = File(localFileUrl)
        uploadUtil.uploadFile(filepath, fileKey, BaseConstant.SERVICE_ADDRESS + "file/img", HashMap<String, String>())
    }

    /**
     * 上传图片成功
     */
    override fun onUploadDone(responseCode: Int, message: String) {
        runOnUiThread {
            hideLoading()
            toast(R.string.upload_success)
            mRemoteFileUrl = message
            mUserIconIv.loadUrl(mRemoteFileUrl)
        }
    }

    override fun onUploadProcess(uploadSize: Int) {
    }

    override fun initUpload(fileSize: Int) {
    }

    /**
     * 获取用户信息成功
     */
    override fun getUserResult(result: UserInfo) {
        mRemoteFileUrl = result.imgurl
        mNickEt.setText(result.nickname)
        mNickEt.setSelection(result.nickname.length)
        if (result.imgurl != "") {
            mUserIconIv.loadUrl(result.imgurl)
        }

    }

    /**
     * 编辑用户资料成功
     */
    override fun onEditUserResult(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

    private fun isBtnEnable(): Boolean {
        return mConfirmBtn.text.isNullOrEmpty().not()
    }

    override fun onGetCartSuccess(result: Int) {
    }

}
