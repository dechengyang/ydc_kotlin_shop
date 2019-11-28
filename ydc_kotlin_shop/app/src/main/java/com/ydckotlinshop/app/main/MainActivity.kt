package com.ydckotlinshop.app.main

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ydc.config.SharePreferenceKey
import com.ydc.datarepository.sphelper.SharedPreferencesHelper
import com.ydckotlinshop.app.R
import com.ydckotlinshop.app.fragment.CategoryFragment
import com.ydckotlinshop.app.fragment.HomeFragment
import com.ydckotlinshop.app.fragment.MyFragment
import com.ydckotlinshop.app.fragment.ShoppingCartFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Fragment管理器 获取Fragment的实例
    private var fragmentManager: FragmentManager? = null
    //Fragment事物   进行添加,移除,替换,以及执行其他动作。
    private var transaction: FragmentTransaction? = null

    private var homeFragment: HomeFragment? = null
    private var categoryFragment: CategoryFragment? = null
    private var shoppingCartFragment:ShoppingCartFragment?=null
    private var  myFragment: MyFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除Title
        if (getSupportActionBar() != null){
            getSupportActionBar()!!.hide();
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        initView();
        switchTab(0)

        var token= SharedPreferencesHelper.get(this,SharePreferenceKey.Object.TOKEN,"")
        var t=token
    }
    fun  initView(){
        tab_home.setOnClickListener(){
            switchTab(0)
        }
        tab_category.setOnClickListener(){
            switchTab(1)
        }
        tab_shoppingcart.setOnClickListener(){
            switchTab(2)
        }
        tab_my.setOnClickListener(){
            switchTab(3)
        }
    }

    fun switchTab(index: Int) {
        initTab()
//        transaction = fragmentManager.beginTransaction()
        transaction = fragmentManager?.beginTransaction();
        hideFragment(transaction!!)
        when (index) {
            0 -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction?.add(R.id.container, homeFragment!!)
                } else {
                    transaction?.show(homeFragment!!)
                }
                tab_home_icon.setImageResource(R.mipmap.home_selected_icon)
                tab_home_text.setTextColor(ContextCompat.getColor(this, R.color.magenta))
            }
            1 -> {
                if (categoryFragment == null) {
                    categoryFragment = CategoryFragment()
                    transaction?.add(R.id.container, categoryFragment!!)
                } else {
                    transaction?.show(categoryFragment!!)
                }
                tab_category_icon.setImageResource(R.mipmap.category_selected_icon)
                tab_category_text.setTextColor(ContextCompat.getColor(this, R.color.magenta))
            }
            2 -> {
                if (shoppingCartFragment == null) {
                    shoppingCartFragment = ShoppingCartFragment()
                    transaction?.add(R.id.container, shoppingCartFragment!!)
                } else {
                    // 如果indexFragment不为空，则直接将它显示出来
                    transaction?.show(shoppingCartFragment!!)
                }
                tab_shoppingcart_icon.setImageResource(R.mipmap.shoppingcart_selected_icon)
                tab_shoppingcart_text.setTextColor(ContextCompat.getColor(this, R.color.magenta))
            }

            3 -> {
                if (myFragment == null) {
                    myFragment = MyFragment()
                    transaction?.add(R.id.container, myFragment!!)
                } else {
                    // 如果indexFragment不为空，则直接将它显示出来
                    transaction?.show(myFragment!!)
                }
                tab_my_icon.setImageResource(R.mipmap.my_selected_icon)
                tab_my_text.setTextColor(ContextCompat.getColor(this, R.color.magenta))
            }
        }

        //transaction?.commit()
        transaction?.commitAllowingStateLoss()//上面的方法是老版本
    }


    //初始化当前的tab
    private fun initTab() {
        tab_home_icon.setImageResource(R.mipmap.home_normal_icon)
        tab_category_icon.setImageResource(R.mipmap.category_normal_icon)
        tab_shoppingcart_icon.setImageResource(R.mipmap.shoppingcart_normal_icon)
        tab_my_icon.setImageResource(R.mipmap.my_normal_icon)

        tab_home_text.setTextColor(ContextCompat.getColor(this, R.color.tab_text))
        tab_category_text.setTextColor(ContextCompat.getColor(this, R.color.tab_text))

        tab_shoppingcart_text.setTextColor(ContextCompat.getColor(this, R.color.tab_text))
        tab_my_text.setTextColor(ContextCompat.getColor(this, R.color.tab_text))
    }


    private fun hideFragment(transaction: FragmentTransaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment!!)
        }
        if (categoryFragment != null) {
            transaction.hide(categoryFragment!!)
        }

        if (shoppingCartFragment != null) {
            transaction.hide(shoppingCartFragment!!)
        }
        if (myFragment != null) {
            transaction.hide(myFragment!!)
        }
    }
}
