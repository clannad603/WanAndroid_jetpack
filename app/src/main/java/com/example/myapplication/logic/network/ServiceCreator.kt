package com.example.myapplication.logic.network

import com.example.myapplication.BuildConfig
import com.example.myapplication.logic.model.Constant.BASE_URL
import com.example.myapplication.logic.model.Constant.CONNECT_TIMEOUT
import com.example.myapplication.logic.model.Constant.READ_TIMEOUT
import com.example.myapplication.logic.model.Constant.WRITE_TIMEOUT
import com.example.myapplication.logic.network.interceptor.CommonInterceptor
import com.example.myapplication.logic.network.interceptor.CookieInterceptor
import com.example.myapplication.logic.network.interceptor.LoginIntercept
import com.example.myapplication.logic.network.liveDataCall.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceCreator {
    fun get(): ApiService {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(CookieInterceptor.initCookieIntercept())
            .addInterceptor(LoginIntercept.initLoginIntercept())
            .addInterceptor(CommonInterceptor.initCommonInterceptor())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}