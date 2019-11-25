package com.ydc.mvp.activity

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import com.ydc.mvp.contract.IPresenterContract
import com.ydc.mvp.view.MvpAppCompatActivity

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
abstract class BaseMvpAppCompatActivity<out P : IPresenterContract> : MvpAppCompatActivity<P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init(savedInstanceState)
        initView()
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected open fun init(savedInstanceState: Bundle?) {}

    protected open fun initView() {}

    protected open fun initData() {}

    override fun getResources(): Resources {
        val res = super.getResources()
        val newConfig = Configuration()
        newConfig.setToDefaults()
        res.updateConfiguration(newConfig, res.displayMetrics)
        return res
    }

    override fun showToast(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    protected open fun goActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    protected open fun goActivity(cls: Class<*>) {
        goActivity(cls, null)
    }
}