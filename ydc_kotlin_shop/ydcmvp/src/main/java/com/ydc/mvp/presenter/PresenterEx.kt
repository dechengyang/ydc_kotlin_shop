package com.ydc.mvp.presenter

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
fun IBasePresenter<*, *>.getContextEx(): Context = when {
    getMvpView() is Activity -> getMvpView() as Activity
    getMvpView() is Fragment -> (getMvpView() as Fragment).activity!!
    else -> throw IllegalStateException("the presenter not found context")
}
