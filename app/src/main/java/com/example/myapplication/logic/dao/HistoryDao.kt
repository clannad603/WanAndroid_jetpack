package com.example.myapplication.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.logic.model.bean.History

/***
 * created by huangrui
 *@Date 2021/8/31 21:10
 */

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History): Long

    @Query("select * from History")
    fun loadAllHistory(): MutableList<History>

}