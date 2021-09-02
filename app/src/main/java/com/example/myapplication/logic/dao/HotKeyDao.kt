package com.example.myapplication.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.logic.model.bean.HotKeyText

/***
 * created by huangrui
 *@Date 2021/8/31 21:11
 */

@Dao
interface HotKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotKey(hotKey: HotKeyText): Long

    @Query("select * from HotKeyText")
    fun loadAllHotKey(): MutableList<HotKeyText>
}