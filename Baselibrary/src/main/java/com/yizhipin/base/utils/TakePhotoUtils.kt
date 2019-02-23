package com.yizhipin.base.utils

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.app.TakePhotoImpl
import org.devio.takephoto.model.TImage
import org.devio.takephoto.model.TResult
import org.devio.takephoto.uitl.TUriParse
import java.io.File


/**
 * 拍照和挑选图片
 * Creator Qi
 * Date 2019/2/21
 */
class TakePhotoUtils(activity: Activity, savedInstanceState: Bundle?) : TakePhoto.TakeResultListener {

    interface OnTakePhotoResult {
        fun takeSuccess(result: TResult?)

        fun takeFail(result: TResult?, msg: String?)

        fun takeCancel()
    }

    private var fileUri: Uri? = null
    private var mActivity: Activity? = activity
    private var mTakePhoto: TakePhotoImpl = TakePhotoImpl(activity, this)
    private var photoFile: File? = null

    init {
        mTakePhoto.onCreate(savedInstanceState)

    }

    private val CODE_REQUEST_FROM_CAPTURE = 1024
    var onTakePhotoResult: OnTakePhotoResult? = null

    fun takePhoto() {
        if (mActivity == null) return
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)// 调用系统的拍照功能
        photoFile = createPictureFile()
        fileUri = if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            Uri.fromFile(photoFile)
        } else {
            //兼容android7.0 使用共享文件的形式
            FileProvider.getUriForFile(mActivity!!, "${mActivity!!.packageName}.fileprovider", photoFile!!)
          /*  val contentValues = ContentValues(1)
            contentValues.put(MediaStore.Images.Media.DATA, file.absolutePath)
             mActivity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)*/
        }
        //为第三方APP授权,还可以通过Intent.addFlag()授权
        /*val resInfoList = mActivity!!.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }*/
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION)
        mActivity!!.startActivityForResult(intent, CODE_REQUEST_FROM_CAPTURE)
    }

    fun pickPhoto(limit: Int) {
        mTakePhoto.onPickMultiple(limit)
    }

    /**
     * 创建照片的临时文件名
     */
    private fun createPictureFile(): File {
        val tempFileName = "${DateUtils.curTime}.jpg"
        val file: File = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            File("${Environment.getExternalStorageDirectory()}/${mActivity?.packageName}/${Environment.DIRECTORY_PICTURES}")
        }else {
            File("${mActivity?.filesDir}/${Environment.DIRECTORY_PICTURES}")
        }
        if (!file.exists())
            file.mkdirs()
        return File(file, tempFileName)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            CODE_REQUEST_FROM_CAPTURE -> {
                if (resultCode == Activity.RESULT_CANCELED) {
                    takeCancel()
                } else if (resultCode == Activity.RESULT_OK) {
                    val tResult = TResult.of(TImage.of(photoFile?.path, TImage.FromType.CAMERA))
                    takeSuccess(tResult)
                }
            }
            else ->
                mTakePhoto.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun takeSuccess(result: TResult?) {
        if (onTakePhotoResult != null) onTakePhotoResult!!.takeSuccess(result)
    }

    override fun takeFail(result: TResult?, msg: String?) {
        if (onTakePhotoResult != null) onTakePhotoResult!!.takeFail(result, msg)
    }

    override fun takeCancel() {
        if (onTakePhotoResult != null) onTakePhotoResult!!.takeCancel()
    }

    fun destroy() {
        if (mActivity != null) {
            mActivity = null
        }
    }
}