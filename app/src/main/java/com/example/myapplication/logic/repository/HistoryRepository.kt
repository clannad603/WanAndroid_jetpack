package com.example.myapplication.logic.repository

import com.example.myapplication.logic.dao.HistoryDao
import com.example.myapplication.logic.model.bean.History

/***
 * created by huangrui
 *@Date 2021/8/31 22:53
 */
class HistoryRepository(private val dao: HistoryDao) {
    fun insert(history: History): Long {
        return dao.insertHistory(history)
    }

    fun loadAllHistory(): MutableList<History> {
        return dao.loadAllHistory()
    }
}