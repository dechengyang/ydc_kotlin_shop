package com.ydckotlinshop.app.interfaceapi

import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.bean.Feed
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
interface IApiService {

    @POST("open/api/v1/login.do")
    fun login(@Query("appid") appid: String,
                          @Query("appsecret") appsecret: String,
                          @Query("username") username: String,
                          @Query("password") password: String): Observable<Feed<TokenBean>>
}