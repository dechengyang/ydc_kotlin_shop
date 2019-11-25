package com.ydc.mvp.presenter

import com.ydc.mvp.contract.IModelContract
import com.ydc.mvp.contract.IViewContract

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
interface IBasePresenter<out V : IViewContract, out M : IModelContract> {

    fun registerModel(): Class<out M>

    fun getMvpView(): V

    fun getModel(): M
}




