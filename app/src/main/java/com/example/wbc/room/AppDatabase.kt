package com.example.wbc.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wbc.entity.SearchHistoryEntity

@Database(entities = [SearchHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): SearchHistoryDao

    companion object {
        private const val DATABASE = "db_history"
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()
            }
            return appDatabase
        }
    }
}