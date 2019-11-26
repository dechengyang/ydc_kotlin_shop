package com.ydc.datarepository.sphelper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper {
    companion object {
        private var sharedPreferencesHelper: SharedPreferencesHelper? = null
        private var sharedPreferences: SharedPreferences? = null
        /**
         * 保存在手机里面的名字
         */
        val FILE_NAME = "sp_data"
        private var editor: SharedPreferences.Editor? = null

        /**
         * @param context
         * @return
         */

        fun getInstance(context: Context): SharedPreferencesHelper {
            if (null == sharedPreferences) {
                sharedPreferencesHelper = SharedPreferencesHelper()
                sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            }
            return sharedPreferencesHelper!!
        }


        /**
         * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
         *
         * @param key
         * @param object
         */
        fun put(context: Context,key: String, `object`: Any?) {
            getInstance(context)
            var `object` = `object`
            editor = sharedPreferences!!.edit()
            if (`object` == null) {
                `object` = ""
            }
            if (`object` is String) {
                editor!!.putString(key, `object` as String?)
            } else if (`object` is Int) {
                editor!!.putInt(key, (`object` as Int?)!!)
            } else if (`object` is Boolean) {
                editor!!.putBoolean(key, (`object` as Boolean?)!!)
            } else if (`object` is Float) {
                editor!!.putFloat(key, (`object` as Float?)!!)
            } else if (`object` is Long) {
                editor!!.putLong(key, (`object` as Long?)!!)
            } else {
                editor!!.putString(key, `object`.toString())
            }
            //editor.apply();
            editor!!.commit()
            //SharedPreferencesCompat.apply(editor);
        }

        /**
         * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
         *
         * @param key           键的值
         * @param defaultObject 默认值
         * @return
         */

        operator fun get(context: Context,key: String, defaultObject: Any): Any? {
            getInstance(context)
            return if (defaultObject is String) {
                sharedPreferences!!.getString(key, defaultObject)
            } else if (defaultObject is Int) {
                sharedPreferences!!.getInt(key, defaultObject)
            } else if (defaultObject is Boolean) {
                sharedPreferences!!.getBoolean(key, defaultObject)
            } else if (defaultObject is Float) {
                sharedPreferences!!.getFloat(key, defaultObject)
            } else if (defaultObject is Long) {
                sharedPreferences!!.getLong(key, defaultObject)
            } else {
                sharedPreferences!!.getString(key, null)
            }
        }

        /**
         * 移除某个key值已经对应的值
         *
         * @param key
         */
        fun remove(key: String) {
            editor!!.remove(key)
            //SharedPreferencesCompat.apply(editor);
        }

        /**
         * 清除所有的数据
         */
        fun clear() {
            editor!!.clear()
            //SharedPreferencesCompat.apply(editor);
        }

        /**
         * 查询某个key是否存在
         *
         * @param key
         * @return
         */
        operator fun contains(key: String): Boolean {
            return sharedPreferences!!.contains(key)
        }

        /**
         * 返回所有的键值对
         *
         * @return
         */
        fun getAll(): Map<String, *> {
            return sharedPreferences!!.all
        }
    }
}