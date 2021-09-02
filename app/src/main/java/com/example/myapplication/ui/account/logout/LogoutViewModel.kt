package com.example.myapplication.ui.account.logout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.AccountRepository
import com.example.myapplication.ui.base.BaseViewModel

class LogoutViewModel : BaseViewModel() {
    private val checkLiveData = MutableLiveData<Boolean>()
    fun check(boolean: Boolean) {
        checkLiveData.value = boolean
    }

    private val logout = Transformations.switchMap(checkLiveData) {
        AccountRepository.logout()
    }
    val logoutErrorMessage = Transformations.map(logout) {
        //loading.value = false
        it.errorMsg
    }
    val logoutErrorCode = Transformations.map(logout) {
        //loading.value = false
        it.errorCode
    }
}