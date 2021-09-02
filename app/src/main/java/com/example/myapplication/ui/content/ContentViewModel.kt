package com.example.myapplication.ui.content

import androidx.lifecycle.viewModelScope
import com.example.myapplication.WanAndroidApplication
import com.example.myapplication.logic.dao.AppDatabase
import com.example.myapplication.logic.model.bean.History
import com.example.myapplication.logic.repository.HistoryRepository
import com.example.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/***
 * created by huangrui
 *@Date 2021/8/31 22:56
 */
class ContentViewModel : BaseViewModel() {
    private val dao = AppDatabase.getDatabase(WanAndroidApplication.context).historyDao()
    private val repository by lazy {
        HistoryRepository(dao)
    }

    fun insertHistory(history: History) = viewModelScope.launch {
        repository.insert(history)
    }
}