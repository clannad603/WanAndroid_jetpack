package com.example.myapplication.ui.account.coindetai

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.AccountRepository
import com.example.myapplication.ui.base.BaseViewModel

class CoinViewModel : BaseViewModel() {
    private val checkLiveData = MutableLiveData<Boolean>()
    private val coinLists = Transformations.switchMap(checkLiveData) {
        AccountRepository.getCoinCount()
    }
    val pageVO = Transformations.map(coinLists) {
        it.data
    }
    val coins = Transformations.map(pageVO) {
        it?.datas
    }

    fun setCheck(boolean: Boolean) {
        checkLiveData.value = boolean
    }

}