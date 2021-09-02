package com.example.myapplication.logic.network.interceptor

import com.example.myapplication.logic.model.Constant
import com.example.myapplication.utils.MyPreference
import okhttp3.Interceptor

object LoginIntercept {
    fun initLoginIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
            val domain = request.url.host

            if (domain.isNotEmpty()) {
                val mCookie by MyPreference(domain, "")
                if (mCookie.isNotEmpty()) {
                    builder.addHeader(Constant.COOKIE_NAME, mCookie)
                }
            }
            val response = chain.proceed(builder.build())
            response
        }
    }
}