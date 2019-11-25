package com.example.kotlinfirst.network

import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import com.zhenggzh.dream.retrofitandrxjavademo.netutils.RetrofitFactory
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import java.util.HashMap
import okhttp3.ResponseBody

/**
 * Created by 眼神 on 2018/3/27.
 * 建议：把功能模块来分别存放不同的请求方法，比如登录注册类LoginSubscribe、电影类MovieSubscribe、天气类WeatherSubscribe
 */

object GitHubSubscribe {

  /**
   * 登录接口
   */
  fun login(appid: String,appsecret: String,username: String,password: String, subscriber: DisposableObserver<Feed<TokenBean>>) {
    val observable = RetrofitFactory.instance.httpApi!!.getUserInfoByName(appid,appsecret,username,password)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
  }

}
