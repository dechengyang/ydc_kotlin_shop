package com.ydckotlinshop.app.presenter

import com.ydc.mvp.presenter.BaseMvpPresenter
import com.example.kotlinfirst.network.GitHubSubscribe
import com.example.kotlinfirst.network.OnSuccessAndFaultListener
import com.example.kotlinfirst.network.OnSuccessAndFaultSub
import com.example.kotlinfirst.utils.GsonUtils
import com.example.kotlinfirst.utils.PermissionUtils
import com.example.kotlinfirst.utils.PreferencesUtil
import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.bean.BaseBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import com.ydckotlinshop.app.contract.LoginContract
import com.ydckotlinshop.app.model.LoginModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import com.ydc.mvp.presenter.getContextEx
import java.util.*

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class LoginPresenter : BaseMvpPresenter<LoginContract.IView, LoginContract.IModel>(), LoginContract.IPresenter{
    override fun login(appid: String,appsecret: String,username: String,password: String) {
            getModel().getUserInfo(appid,appsecret,username,password, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener<TokenBean> {
                override fun onSuccess(data: TokenBean) {

                    var c=data.token;
                    var token=data.token;
                    getMvpView().loginSuccess(data)
                    //成功
//                    val userBean = GsonUtils.fromJson(result, UserBean::class.java)
//
//                    Observable.just(userBean).subscribe(Consumer {
//                        PreferencesUtil.saveValue(PreferencesUtil.USER_ID,userBean?.id)
//                        PreferencesUtil.saveValue(PreferencesUtil.USER_NAME,userBean?.name)
//                        PreferencesUtil.saveValue(PreferencesUtil.USER_AVATAR,userBean?.avatar_url)
//                        PreferencesUtil.saveValue(PreferencesUtil.USER_BLOG,userBean?.blog)
//                    }).dispose()
//
//                    getMvpView().loginSuccess(userBean!!)
                }

                override fun onFault(errorMsg: String) {
                    //失败
                    getMvpView().loginError(errorMsg)
                }

            },getContextEx()))

    }

    override fun registerModel() = LoginModel::class.java

}
