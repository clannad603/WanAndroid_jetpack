package com.example.myapplication.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.WanAndroidApplication
import com.example.myapplication.logic.dao.AppDatabase
import com.example.myapplication.logic.model.bean.History
import com.example.myapplication.logic.repository.HistoryRepository
import com.example.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/***
 * created by huangrui
 *@Date 2021/8/31 22:37
 */
class HistoryViewModel : BaseViewModel() {
    private val dao = AppDatabase.getDatabase(WanAndroidApplication.context).historyDao()
    private val repository by lazy {
        HistoryRepository(dao)
    }
    private val _mHistory = MutableLiveData<MutableList<History>>()
    val mHistory: LiveData<MutableList<History>>
        get() = _mHistory

    fun getHistory() = viewModelScope.launch {
        _mHistory.value = repository.loadAllHistory()
    }
}