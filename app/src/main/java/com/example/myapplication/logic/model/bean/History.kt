package com.example.myapplication.logic.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * created by huangrui
 *@Date 2021/8/31 21:08
 */

@Entity
data class History(
    @PrimaryKey()
    val title: String,
    val uri: String,
    val desc: String
) {

}
