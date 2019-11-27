package com.ydckotlinshop.app.contract

import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.account.bean.UserBean
import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import com.ydc.mvp.contract.IPresenterContract
import com.ydc.mvp.contract.IViewContract
import com.ydc.mvp.contract.IModelContract
import okhttp3.ResponseBody

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
interface LoginContract {

  interface IView : IViewContract{
    fun loginSuccess(tokenBean: TokenBean)
    fun loginError(errorMsg:String?)
  }

  interface IPresenter : IPresenterContract {
    fun login(appid: String,appsecret: String,username: String,password: String)
  }

  interface IModel : IModelContract{
    fun login(appid: String,appsecret: String,username: String,password: String, subscriber: DisposableObserver<Feed<TokenBean>>): Observable<Feed<TokenBean>>
  }
}
