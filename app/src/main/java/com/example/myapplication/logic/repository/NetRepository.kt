package com.example.myapplication.logic.repository

import com.example.myapplication.logic.network.ServiceCreator

open class NetRepository : BaseRepository {
    val api by lazy {
        ServiceCreator.get()
    }
}