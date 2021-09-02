package com.example.myapplication.ui.mainview.fragmentcollect.square

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.ArticleRepository
import com.example.myapplication.ui.base.BaseViewModel

class SquareViewModel : BaseViewModel() {
    private val pageLiveData = MutableLiveData<Int>()
    private val articleLists = Transformations.switchMap(pageLiveData) { it ->
        ArticleRepository.getSquare(it)
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
}