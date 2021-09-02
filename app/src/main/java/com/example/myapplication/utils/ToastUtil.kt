package com.example.myapplication.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

object ToastUtil {
    fun showLongToast(context: Context?, message: String?) {
        if (!TextUtils.isEmpty(message)) Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }

    fun showShortToast(context: Context?, message: String?) {
        if (!TextUtils.isEmpty(message)) Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    public final fun show(msg: kotlin.String): kotlin.Unit { /* compiled code */
    }
}