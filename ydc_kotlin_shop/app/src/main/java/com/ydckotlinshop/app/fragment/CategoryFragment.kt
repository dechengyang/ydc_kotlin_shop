package com.ydckotlinshop.app.fragment

import com.ydckotlinshop.app.R
import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.contract.LoginContract
import com.ydckotlinshop.app.presenter.LoginPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

class CategoryFragment : BaseMvpFragment<LoginContract.IPresenter>(), LoginContract.IView{

    override fun getLayoutId(): Int = R.layout.fragment_category
    override fun  initView(){

    }
    override fun loginError(errorMsg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginSuccess(tokenBean: TokenBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerPresenter() = LoginPresenter::class.java


}