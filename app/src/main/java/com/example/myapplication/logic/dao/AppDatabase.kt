package com.example.myapplication.logic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.logic.model.bean.History
import com.example.myapplication.logic.model.bean.HotKeyText

@Database(version = 1, entities = [HotKeyText::class, History::class], exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
    abstract fun hotKeyDao(): HotKeyDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build().apply {
                    instance = this
                }
        }
    }
}