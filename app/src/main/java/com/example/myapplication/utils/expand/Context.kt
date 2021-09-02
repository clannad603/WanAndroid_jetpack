package com.example.myapplication.utils.expand

import android.content.Context
import android.content.res.Configuration

/***
 * created by huangrui
 *@Date 2021/8/31 20:52
 */
fun Context.getDarkModeStatus(): Boolean {
    val mode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return mode == Configuration.UI_MODE_NIGHT_YES
}