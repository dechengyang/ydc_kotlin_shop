package com.ydckotlinshop.app.contract

import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.account.bean.UserBean
import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract
import okhttp3.ResponseBody

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
interface LoginContract {

  interface IView : IViewContract{
    fun loginSuccess(tokenBean: TokenBean)
    fun loginError(errorMsg:String?)
  }

  interface IPresenter : IPresenterContract {
    fun login(appid: String,appsecret: String,username: String,password: String)
  }

  interface IModel : IModelContract{
    fun getUserInfo(appid: String,appsecret: String,username: String,password: String, subscriber: DisposableObserver<Feed<TokenBean>>): Observable<Feed<TokenBean>>
  }
}