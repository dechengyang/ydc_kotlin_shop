package com.ydc.base.util

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast


/**
 * Created by decheng.yang on 2018/5/31.
 *
 *
 * Toast统一管理类
 */


class ToastUtil private constructor() {

    private val layoutParams: ViewGroup.LayoutParams? = null

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        var isShow = true

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showShort(context: Context, message: CharSequence) {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showShort(context: Context, message: Int) {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * 长时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showLong(context: Context, message: CharSequence) {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        /**
         * 长时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showLong(context: Context, message: Int) {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param context
         * @param message
         * @param duration
         */
        fun show(context: Context, message: CharSequence, duration: Int) {
            if (isShow)
                Toast.makeText(context, message, duration).show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param context
         * @param message
         * @param duration
         */
        fun show(context: Context, message: Int, duration: Int) {
            if (isShow)
                Toast.makeText(context, message, duration).show()
        }
    }
}