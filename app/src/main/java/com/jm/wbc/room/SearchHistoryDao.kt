package com.jm.wbc.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jm.wbc.data.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM SearchHistoryEntity ORDER BY id DESC")
    fun getAllItem(): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: SearchHistoryEntity)

    @Delete
    suspend fun deleteItem(item: SearchHistoryEntity)

    @Query("DELETE FROM SearchHistoryEntity")
    suspend fun deleteAllItem()
}