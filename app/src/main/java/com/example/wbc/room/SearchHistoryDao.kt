package com.example.wbc.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wbc.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM SearchHistoryEntity")
    fun getAllItem(): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: SearchHistoryEntity)

    @Delete
    suspend fun deleteItem(item: SearchHistoryEntity)
}