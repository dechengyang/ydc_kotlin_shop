package  com.ydckotlinshop.app.model

import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.bean.CommonBean
import com.ydckotlinshop.app.bean.Feed
import com.ydckotlinshop.app.contract.LoginContract
import com.zhenggzh.dream.retrofitandrxjavademo.netutils.RetrofitFactory
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import com.ydc.mvp.model.BaseModel
import com.ydc.mvp.presenter.getContextEx
import okhttp3.ResponseBody

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class LoginModel : BaseModel(), LoginContract.IModel {
  override fun getUserInfo(appid: String,appsecret: String,username: String,password: String, subscriber: DisposableObserver<Feed<TokenBean>>): Observable<Feed<TokenBean>> {

    val observable = RetrofitFactory.instance.httpApi!!.getUserInfoByName(appid,appsecret,username,password)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
    return observable
  }
}