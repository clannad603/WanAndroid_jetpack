package com.example.myapplication.logic.repository

import com.example.myapplication.logic.dao.HotKeyDao
import com.example.myapplication.logic.model.bean.HotKeyText

/***
 * created by huangrui
 *@Date 2021/8/31 23:32
 */
class HotKeyRepository(private val dao: HotKeyDao) {
    fun insertHotKey(hotKey: HotKeyText): Long {
        return dao.insertHotKey(hotKey)
    }

    fun loadAllHotKey(): MutableList<HotKeyText> {
        return dao.loadAllHotKey()
    }
}