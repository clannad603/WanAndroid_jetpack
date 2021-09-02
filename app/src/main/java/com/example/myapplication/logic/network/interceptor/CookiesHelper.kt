package com.example.myapplication.logic.network.interceptor


import com.example.myapplication.utils.MyPreference

object CookiesHelper {
    /***
     * 组装cookies
     */
    fun parseCookie(it: List<String>): String {
        if (it.isEmpty()) {
            return ""
        }

        val stringBuilder = StringBuilder()

        it.forEach { cookie ->
            stringBuilder.append(cookie).append(";")
        }

        if (stringBuilder.isEmpty()) {
            return ""
        }
        //末尾的";"去掉
        return stringBuilder.deleteCharAt(stringBuilder.length - 1).toString()
    }

    fun saveCookie(domain: String?, parseCookie: String) {
        domain?.let {
            var resutl: String by MyPreference(it, parseCookie)
            resutl = parseCookie
        }
    }
}