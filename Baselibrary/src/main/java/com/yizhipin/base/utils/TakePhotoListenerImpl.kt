package com.yizhipin.base.utils

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.mvp.view.PhotoView
import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.model.TResult
import java.io.File

/**
 * 拍照监听基本实现
 * Creator Qi
 * Date 2019/2/23
 */
class TakePhotoListenerImpl(val view: PhotoView) : TakePhotoUtils.OnTakePhotoResult, TakePhoto.TakeResultListener, UploadUtil.OnUploadProcessListener {
    override fun onUploadDone(responseCode: Int, message: String?) {
        view.showIcon(message)
    }

    override fun onUploadProcess(uploadSize: Int) {
    }

    override fun initUpload(fileSize: Int) {
    }

    override fun takeSuccess(result: TResult?) {
        val localFileUrl = result?.image?.originalPath
        val fileKey = "avatarFile"
        val uploadUtil = UploadUtil.getInstance()
        uploadUtil.setOnUploadProcessListener(this) //设置监听器监听上传状态
        val filepath = File(localFileUrl)
        uploadUtil.uploadFile(filepath, fileKey, BaseConstant.SERVICE_ADDRESS + "file/img", HashMap<String, String>())
    }

    override fun takeFail(result: TResult?, msg: String?) {
    }

    override fun takeCancel() {
    }
}