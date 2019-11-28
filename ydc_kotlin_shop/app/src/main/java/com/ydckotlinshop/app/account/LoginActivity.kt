package com.ydckotlinshop.app.account

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ydc.base.util.ToastUtil
import com.ydc.base.util.ValidateUtil
import com.ydc.config.ApiConfig
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


    private var loginType = 1//0:密码登录 1:验证码登录 2：第三方登录
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login_layout)
//    }
    override fun  initView(){
        btn_login.setOnClickListener {
            if (isAllow()) {
                loginWithPwd()
            }
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


    private fun loginWithPwd() {
        val params = HashMap<String, String>()
        params["appid"] = Constant.appId
        params["appsecret"] = Constant.SECRETKEY
        params["username"] = edt_phone.text.toString()
        params["password"] = edt_pwd.text.toString();
        getPresenter().login(params)
    }

    fun isAllow(): Boolean {
        var flag = true
        try {
            if (TextUtils.isEmpty(edt_phone.text.toString().trim { it <= ' ' })) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show()
                flag = false
                return flag
            } else if (!ValidateUtil.checkPhoneNumber(edt_phone.text.toString().trim { it <= ' ' })) {
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show()
                flag = false
                return flag
            }
            if (TextUtils.isEmpty(edt_pwd.text.toString().trim { it <= ' ' })) {
                Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show()
                flag = false
                return flag
            } else {
                //                if (loginType == 0) {
                //                    if (edt_pwd.getText().toString().trim().length() < 6 || edt_pwd.getText().toString().trim().length() > 16) {
                //                        Toast.makeText(this, "请输入6-16位字母或数字的密码!", Toast.LENGTH_SHORT).show();
                //                    }
                //                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return flag
    }


    override fun loginError(errorMsg: String?) {
        ToastUtil.showShort(this, errorMsg.toString())
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