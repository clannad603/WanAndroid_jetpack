package com.example.myapplication.ui.officialaccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.ArticleRepository
import com.example.myapplication.ui.base.BaseViewModel

class OfficialAccountViewModel : BaseViewModel() {
    private val childLiveData = MutableLiveData<Int>()
    private val articleLists = Transformations.switchMap(childLiveData) { it ->
        ArticleRepository.getOfficialChild(it)
    }
    val pageVO = Transformations.map(articleLists) {
        it.data
    }
    val articles = Transformations.map(pageVO) {
        it?.datas
    }

    fun giveCheck(int: Int) {
        childLiveData.value = int
    }

    private val checkLiveData = MutableLiveData<Boolean>()
    private val projectBack = Transformations.switchMap(checkLiveData) {
        ArticleRepository.getOfficialAccount()
    }

    val officialList = Transformations.map(projectBack) {
        it.data
    }

    fun setCheck(boolean: Boolean) {
        checkLiveData.value = boolean
    }

}