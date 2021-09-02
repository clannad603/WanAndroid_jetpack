package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.smtt.sdk.QbSdk

class WanAndroidApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initTencentX5()
    }

    private fun initTencentX5() {
        val cb = object : QbSdk.PreInitCallback {

            override fun onViewInitFinished(arg0: Boolean) {
                Log.e("HLQ_Struggle", "x5 內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核：$arg0")
            }

            override fun onCoreInitFinished() {
                Log.e("HLQ_Struggle", "x5 內核初始化 onCoreInitFinished")
            }
        }
        QbSdk.initX5Environment(applicationContext, cb)
    }
}