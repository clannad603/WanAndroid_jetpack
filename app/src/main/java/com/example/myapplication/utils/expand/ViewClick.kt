package com.example.myapplication.utils.expand

import android.view.View
import com.example.myapplication.utils.expand.ViewClickDelay.SPACE_TIME
import com.example.myapplication.utils.expand.ViewClickDelay.hash
import com.example.myapplication.utils.expand.ViewClickDelay.lastClickTime

object ViewClickDelay {
    var hash: Int = 0
    var lastClickTime: Long = 0
    var SPACE_TIME: Long = 1000
}

/***
 * 扩展函数专用,此处使用了高阶函数
 */
infix fun View.clicks(clickAction: () -> Unit) {
    this.setOnClickListener {
        if (this.hashCode() != hash) {
            hash = this.hashCode()
            lastClickTime = System.currentTimeMillis()
            clickAction()
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > SPACE_TIME) {
                lastClickTime = System.currentTimeMillis()
                clickAction()
            }
        }
    }
}

/**
 * 防止重复点击
 */
var lastClickTime = 0L
fun View.setOnclickNoRepeat(interval: Long = 500, onClick: (View) -> Unit) {
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)) {
            return@setOnClickListener
        }
        /***
         * return @有点像c语言中goto
         */
        lastClickTime = currentTime
        onClick.invoke(it)
    }
}
