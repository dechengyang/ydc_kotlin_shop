package com.ydc.mvp.contract

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
interface IViewContract {

    fun showToast(resId: Int)

    fun showToast(text: String?)

}