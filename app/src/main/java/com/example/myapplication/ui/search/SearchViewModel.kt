package com.example.myapplication.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.myapplication.WanAndroidApplication
import com.example.myapplication.logic.dao.AppDatabase
import com.example.myapplication.logic.model.BaseResponse
import com.example.myapplication.logic.model.bean.HotKeyText
import com.example.myapplication.logic.model.bean.HotKeyVO
import com.example.myapplication.logic.model.bean.SearchInfo
import com.example.myapplication.logic.repository.ArticleRepository
import com.example.myapplication.logic.repository.HotKeyRepository
import com.example.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel() {
    private val searchInfoLiveData = MutableLiveData<SearchInfo>()
    private val articleLists = Transformations.switchMap(searchInfoLiveData) { it ->
        ArticleRepository.searchArticleList(it)
    }
    val pageVO = Transformations.map(articleLists) {
        it.data
    }
    val articles = Transformations.map(pageVO) {
        it?.datas
    }


    fun giveSearch(searchInfo: SearchInfo) {
        searchInfoLiveData.value = searchInfo
    }

    private val start = MutableLiveData<Boolean>()
    fun getHotKey(boolean: Boolean) {
        start.value = boolean
    }

    private val _hotkey = Transformations.switchMap(start) {
        ArticleRepository.getHotKey()
    }
    val hotkey: LiveData<BaseResponse<MutableList<HotKeyVO>>>
        get() = _hotkey


    private val dao = AppDatabase.getDatabase(WanAndroidApplication.context).hotKeyDao()
    private val repository by lazy {
        HotKeyRepository(dao)
    }
    private val _mHotKey = MutableLiveData<MutableList<HotKeyText>>()
    val mHotKey: LiveData<MutableList<HotKeyText>>
        get() = _mHotKey

    fun getMyHotKey() = viewModelScope.launch {
        _mHotKey.value = repository.loadAllHotKey()
    }

    fun insertHotKey(hotkey: HotKeyText) = viewModelScope.launch {
        repository.insertHotKey(hotkey)
    }
}