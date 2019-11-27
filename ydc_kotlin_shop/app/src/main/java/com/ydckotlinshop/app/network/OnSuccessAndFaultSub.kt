package com.example.kotlinfirst.network

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.example.kotlinfirst.utils.CompressUtils
import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


/**
 * @Description 处理网络数据处理完成后的回调响应（观察者）
 * @Author ydc
 * @CreateDate 2019/11/27
 * @Version 1.0
 */
class OnSuccessAndFaultSub<T> constructor(mOnSuccessAndFaultListener: OnSuccessAndFaultListener<T>): DisposableObserver<Feed<T>>(), ProgressCancelListener {

  private object Status {
    const val SUCCESS = "1000"
  }
  private var showProgress = true
  private var mOnSuccessAndFaultListener: OnSuccessAndFaultListener<T>
  private var mProgressDialog : ProgressDialog? =null

  init {
    this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
  }

  constructor(mOnSuccessAndFaultListener: OnSuccessAndFaultListener<T>,context: Context):this(mOnSuccessAndFaultListener){
    this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    mProgressDialog= ProgressDialog(context)
    mProgressDialog!!.setMessage("正在加载请稍后~")
  }
  constructor(mOnSuccessAndFaultListener: OnSuccessAndFaultListener<T>,context: Context,showProgress:Boolean):this(mOnSuccessAndFaultListener){
    this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    this.showProgress = showProgress
    mProgressDialog= ProgressDialog(context)
  }

  private fun showProgressDialog(){
    if (showProgress && null != mProgressDialog) {
      mProgressDialog!!.show()
    }
  }
  private fun dismissProgressDialog() {
    if (showProgress && null != mProgressDialog) {
      mProgressDialog!!.dismiss()
    }
  }

  /**
   * 订阅开始时调用
   * 显示ProgressDialog
   */
  override fun onStart() {
    super.onStart()
    showProgressDialog()
  }

  /**
   * 完成，隐藏ProgressDialog
   */
  override fun onComplete() {
    dismissProgressDialog()
    mProgressDialog = null
  }


  /**
   * 网络请求响应回调
   */
  override fun onNext(t: Feed<T>) {
    try {
    if(t.code.equals(Status.SUCCESS)){
      mOnSuccessAndFaultListener.onSuccess(t.message)
      mOnSuccessAndFaultListener.onSuccess(t.data)
      }else{
      mOnSuccessAndFaultListener.onFault("网络连接超时")
    }
    } catch (e: Exception) {
      e.printStackTrace()
    }

  }

  /**
   * 对错误进行统一处理
   * 隐藏ProgressDialog
   */
  override fun onError(e: Throwable) {
    try {

      if (e is SocketTimeoutException) {//请求超时
      } else if (e is ConnectException) {//网络连接超时
        //                ToastManager.showShortToast("网络连接超时");
        mOnSuccessAndFaultListener.onFault("网络连接超时")
      } else if (e is SSLHandshakeException) {//安全证书异常
        //                ToastManager.showShortToast("安全证书异常");
        mOnSuccessAndFaultListener.onFault("安全证书异常")
      } else if (e is HttpException) {//请求的地址不存在
        val code = (e as HttpException).code()
        if (code == 504) {
          //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
          mOnSuccessAndFaultListener.onFault("网络异常，请检查您的网络状态")
        } else if (code == 404) {
          //                    ToastManager.showShortToast("请求的地址不存在");
          mOnSuccessAndFaultListener.onFault("请求的地址不存在")
        } else {
          //                    ToastManager.showShortToast("请求失败");
          mOnSuccessAndFaultListener.onFault("请求失败")
        }
      } else if (e is UnknownHostException) {//域名解析失败
        //                ToastManager.showShortToast("域名解析失败");
        mOnSuccessAndFaultListener.onFault("域名解析失败")
      } else {
        //                ToastManager.showShortToast("error:" + e.getMessage());
        mOnSuccessAndFaultListener.onFault("error:" + e.message)
      }
    } catch (e2: Exception) {
      e2.printStackTrace()
    } finally {
      Log.e("OnSuccessAndFaultSub", "error:" + e.message)
      //            mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
      dismissProgressDialog()
      mProgressDialog = null

    }
  }

  /**
   * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
   */
  override fun onCancelProgress() {
    if (!this.isDisposed) {
      this.dispose()
    }
  }
}