package com.jm.wbc.repository.room

import com.jm.wbc.data.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    fun getAllHistory(): Flow<List<SearchHistoryEntity>>

    suspend fun insertHistory(searchHistoryEntity: SearchHistoryEntity)

    suspend fun deleteHistory(searchHistoryEntity: SearchHistoryEntity)

    suspend fun deleteAllHistory()
}