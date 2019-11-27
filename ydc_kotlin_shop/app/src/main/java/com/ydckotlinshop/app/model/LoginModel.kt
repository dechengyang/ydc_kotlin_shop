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
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
class LoginModel : BaseModel(), LoginContract.IModel {
  override fun login(appid: String,appsecret: String,username: String,password: String, subscriber: DisposableObserver<Feed<TokenBean>>): Observable<Feed<TokenBean>> {

    val observable = RetrofitFactory.instance.httpApi!!.login(appid,appsecret,username,password)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
    return observable
  }
}