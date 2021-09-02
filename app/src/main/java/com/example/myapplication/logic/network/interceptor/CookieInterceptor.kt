package com.example.myapplication.logic.network.interceptor

import android.util.Log
import com.example.myapplication.logic.model.Constant
import okhttp3.Interceptor

object CookieInterceptor {
    fun initCookieIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            val requestUrl = request.url.toString()
            val domain = request.url.host

            //只保存登录或者注册
            if (requestUrl.contains(Constant.LOGIN_KEY) || requestUrl.contains(Constant.REGISTER_KEY)) {
                val mCookie = response.headers(Constant.SET_COOKIE_KEY)
                mCookie?.let {
                    CookiesHelper.saveCookie(domain, CookiesHelper.parseCookie(it))
                }
                Log.d("coockie", CookiesHelper.parseCookie(mCookie))
            }
            response
        }
    }
}