package com.example.wbc.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wbc.data.entity.SearchHistoryEntity

@Database(entities = [SearchHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao

}