package com.example.wbc.repository

import com.example.wbc.data.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    fun getAllHistory(): Flow<List<SearchHistoryEntity>>

    suspend fun insertHistory(searchHistoryEntity: SearchHistoryEntity)

    suspend fun deleteHistory(searchHistoryEntity: SearchHistoryEntity)
}