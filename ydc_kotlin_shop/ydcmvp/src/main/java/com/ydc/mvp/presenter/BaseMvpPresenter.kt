package com.ydc.mvp.presenter

import com.ydc.mvp.contract.IModelContract
import com.ydc.mvp.contract.IPresenterContract
import com.ydc.mvp.contract.IViewContract

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
abstract class BaseMvpPresenter<out V : IViewContract, out M : IModelContract> : IBasePresenter<V, M>, IPresenterContract {

    private var mMVPView: V? = null
    private var mModel: M? = null

    @Suppress("UNCHECKED_CAST")
    override fun register(mvpView: IViewContract) {
        mMVPView = mvpView as V
        mModel = registerModel().newInstance()
    }

    override fun getMvpView() = mMVPView!!

    override fun getModel() = mModel!!

    override fun onCreate() {
        mModel!!.onCreate()
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        if (mMVPView != null) mMVPView = null
        if (mModel != null) {
            mModel!!.onDestroy()
            mModel = null
        }
    }

}


