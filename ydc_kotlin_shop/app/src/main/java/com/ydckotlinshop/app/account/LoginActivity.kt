package com.ydckotlinshop.app.account

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ydc.config.Constant
import com.ydc.config.SharePreferenceKey
import com.ydc.datarepository.sphelper.SharedPreferencesHelper
import com.ydckotlinshop.app.R
import com.ydckotlinshop.app.account.bean.TokenBean
import com.ydckotlinshop.app.account.bean.UserBean
import com.ydckotlinshop.app.contract.LoginContract
import com.ydckotlinshop.app.main.MainActivity
import com.ydckotlinshop.app.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import com.ydc.mvp.activity.BaseMvpActivity
import java.util.HashMap

class LoginActivity: BaseMvpActivity<LoginContract.IPresenter>(), LoginContract.IView{

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login_layout)
//    }
    override fun  initView(){
        btn_login.setOnClickListener {
            val params = HashMap<String, String>()
            params["appid"] = Constant.appId
            params["appsecret"] = Constant.SECRETKEY
            params["username"] = "18721568888"
            params["password"] = "123456"
            getPresenter().login(params)
        }
        ll_pwd.setOnClickListener {
            tv_pwd.setTextColor(-0xcccccd)
            tv_kuaiji.setTextColor(-0x666667)
            line_kuaiji.visibility = View.GONE
            line_pwd.visibility = View.VISIBLE
            ll_agreement.visibility = View.GONE
            ll_code.visibility = View.GONE
            ll_pwd_value.visibility = View.VISIBLE
        }
        rl_kuaijie_login.setOnClickListener {
            ll_code.visibility = View.VISIBLE
            ll_pwd_value.visibility = View.GONE
            tv_pwd.setTextColor(-0x666667)
            tv_kuaiji.setTextColor(-0xcccccd)
            line_kuaiji.visibility = View.VISIBLE
            line_pwd.visibility = View.GONE
            ll_agreement.visibility = View.VISIBLE
        }

    }

    override fun loginError(errorMsg: String?) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show();
    }
    override fun showToast(text: String?) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    override fun loginSuccess(tokenBean: TokenBean) {
        var  token=tokenBean.token
        SharedPreferencesHelper.put(this,SharePreferenceKey.Object.TOKEN,token)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun registerPresenter() = LoginPresenter::class.java

    override fun getLayoutId(): Int = R.layout.activity_login
}