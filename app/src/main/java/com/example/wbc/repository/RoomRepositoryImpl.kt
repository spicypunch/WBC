package com.example.wbc.repository

import com.example.wbc.di.IoDispatcher
import com.example.wbc.entity.SearchHistoryEntity
import com.example.wbc.room.SearchHistoryDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): RoomRepository {
    override fun getAllHistory(): Flow<List<SearchHistoryEntity>> = searchHistoryDao.getAllItem()

    override suspend fun insertHistory(searchHistoryEntity: SearchHistoryEntity) {
        searchHistoryDao.insertItem(searchHistoryEntity)
    }

    override suspend fun deleteHistory(searchHistoryEntity: SearchHistoryEntity) {
        searchHistoryDao.deleteItem(searchHistoryEntity)
    }
}