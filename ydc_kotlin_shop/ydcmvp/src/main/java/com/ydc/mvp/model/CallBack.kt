package com.ydc.mvp.model

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
abstract class CallBack<T> {

    open fun onNext(data: T) {
    }

    open fun onError(t: Throwable) {
    }

}
