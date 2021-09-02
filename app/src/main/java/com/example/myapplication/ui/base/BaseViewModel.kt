package com.example.myapplication.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.logic.model.BaseResponse
import com.example.myapplication.logic.repository.ArticleRepository
import org.json.JSONObject

open class BaseViewModel : ViewModel() {

    protected val page = MutableLiveData<Int>()
    val isLogin = MutableLiveData<Boolean>()
    fun makeLogin(boolean: Boolean) {
        isLogin.value = boolean
    }

    private val collectId = MutableLiveData<Int>()
    fun collectArticle(id: Int) {
        collectId.value = id
    }

    private val _collect = Transformations.switchMap(collectId) {
        ArticleRepository.collectArticle(it)
    }
    val collect: LiveData<BaseResponse<JSONObject>>
        get() = _collect

    private val unCollectId = MutableLiveData<Int>()
    fun unCollectArticle(id: Int) {
        unCollectId.value = id
    }

    private val _unCollect = Transformations.switchMap(unCollectId) {
        ArticleRepository.unCollect(it)
    }
    val unCollect: LiveData<BaseResponse<JSONObject>>
        get() = _unCollect
}