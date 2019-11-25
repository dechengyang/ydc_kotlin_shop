package com.example.kotlinfirst.network

import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed

interface OnSuccessAndFaultListener<T> {

  fun onSuccess(data: T)

  fun onFault(errorMsg: String)
}