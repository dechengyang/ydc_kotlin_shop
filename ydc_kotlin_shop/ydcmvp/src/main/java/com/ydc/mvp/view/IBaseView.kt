package com.ydc.mvp.view

import com.ydc.mvp.contract.IPresenterContract

/**
 * @Description MVP之V层 是所有VIEW的基类，其他类可以继承该类
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
interface IBaseView<out P : IPresenterContract> {
    fun registerPresenter(): Class<out P>
}
