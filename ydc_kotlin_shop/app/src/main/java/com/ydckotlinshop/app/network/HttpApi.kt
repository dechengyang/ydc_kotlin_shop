package com.example.kotlinfirst.network

import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface HttpApi {

  @POST("open/api/v1/login.do")
  fun getUserInfoByName(@Query("appid") appid: String,
             @Query("appsecret") appsecret: String,
             @Query("username") username: String,
             @Query("password") password: String): Observable<Feed<TokenBean>>

}