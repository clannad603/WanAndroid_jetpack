package com.example.myapplication.ui.mainview.fragmentcollect.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.ArticleRepository
import com.example.myapplication.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {
    private val pageLiveData = MutableLiveData<Int>()

    private val articleLists = Transformations.switchMap(pageLiveData) { it ->
        ArticleRepository.getArticleList(it)
    }


    val pageVO = Transformations.map(articleLists) {
        it.data
    }
    val articles = Transformations.map(pageVO) {
        it?.datas
    }

    fun giveCheck(page: Int) {
        pageLiveData.value = page
    }

    private val checkLiveData = MutableLiveData<Boolean>()
    private val _banner = Transformations.switchMap(checkLiveData) {
        ArticleRepository.getBanner()
    }

    fun getBanner(boolean: Boolean) {
        checkLiveData.value = boolean
    }

    val banner = Transformations.map(_banner) {
        it.data
    }


}