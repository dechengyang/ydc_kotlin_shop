package com.ydc.mvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ydc.mvp.contract.IPresenterContract
import com.ydc.mvp.contract.IViewContract

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
abstract class MvpFragment<out P : IPresenterContract> : Fragment(), IBaseView<P>, IViewContract {

    private var mPresenter: P? = null

    protected fun getPresenter() = mPresenter!!

    private fun initPresenter() {
        mPresenter = registerPresenter().newInstance()
        mPresenter?.register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        mPresenter?.onCreate()
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.onDestroy()
            mPresenter = null
        }
    }
}
