package com.example.myapplication.logic.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * created by huangrui
 *@Date 2021/8/31 21:10
 */

@Entity
data class HotKeyText(
    @PrimaryKey()
    val text: String

)


