package com.example.myapplication.ui.account.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.AccountRepository
import com.example.myapplication.ui.base.BaseViewModel

class MineViewModel : BaseViewModel() {
    private val mutableLiveData = MutableLiveData<String>()
    private val _userInfo = Transformations.switchMap(isLogin) {
        AccountRepository.getUserInfo()
    }

    val userInfo = Transformations.map(_userInfo) {
        it.data
    }

}