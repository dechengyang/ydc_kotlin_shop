package com.example.kotlinfirst.network

import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed


/**
 * @Description 网络请求数据回调
 * @Author ydc
 * @CreateDate 2019/11/27
 * @Version 1.0
 */

interface OnSuccessAndFaultListener<T> {
  fun onSuccess(data: T)
  fun onSuccess(successMsg: String)
  fun onFault(errorMsg: String)
}