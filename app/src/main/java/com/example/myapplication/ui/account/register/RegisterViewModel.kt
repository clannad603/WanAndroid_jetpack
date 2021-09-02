package com.example.myapplication.ui.account.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.model.bean.RegisterInfo
import com.example.myapplication.logic.repository.AccountRepository
import com.example.myapplication.ui.base.BaseViewModel

class RegisterViewModel : BaseViewModel() {

    private val haveBoston = MutableLiveData<RegisterInfo>()
    private val login = Transformations.switchMap(haveBoston) { it ->
        AccountRepository.register(it.username, it.password, it.repassword)
    }

    fun sendInfo(registerInfo: RegisterInfo) {
        haveBoston.value = registerInfo
    }

    /***
     * 转化
     */
    val loginUser = Transformations.map(login) {
        //  loading.value = false
        it.errorCode
    }
    val loginErrorMessage = Transformations.map(login) {
        //loading.value = false
        it.errorMsg
    }


}