package com.ydc.base.util

import java.util.regex.Pattern


/**
 * Created by decheng.yang on 2018/2/22.
 */

object ValidateUtil {
    /**
     * 判断手机号格式
     *
     * @param phoneNumber 手机号码
     * @return true(正确的手机号13 14 15 17 18);false 非
     */
    fun checkPhoneNumber(phoneNumber: String): Boolean {
        val p = Pattern
            .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(16[0-9])|(19[0-9]))\\d{8}$")
        val m = p.matcher(phoneNumber)
        return m.matches()
    }

    /**
     * 车牌号验证
     */
    fun isCarCard(carCard: String): Boolean {
        val p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z_0-9]{5,7}$")
        val m = p.matcher(carCard)
        return m.matches()
    }
}