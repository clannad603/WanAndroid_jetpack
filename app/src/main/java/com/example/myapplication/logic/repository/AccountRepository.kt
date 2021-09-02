package com.example.myapplication.logic.repository

object AccountRepository : NetRepository() {
    fun login(username: String, password: String) =
        api.login(username, password)

    fun register(username: String, password: String, repassword: String) =
        api.register(username, password, repassword)

    fun getUserInfo() =
        api.getMyCoin()

    fun logout() = api.logout()
    fun getCoinCount() = api.getCoinChange()

}