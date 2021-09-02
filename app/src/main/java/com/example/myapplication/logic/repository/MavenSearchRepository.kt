package com.example.myapplication.logic.repository

class MavenSearchRepository : NetRepository() {
    fun getList() =
        api.getMavenList()
}