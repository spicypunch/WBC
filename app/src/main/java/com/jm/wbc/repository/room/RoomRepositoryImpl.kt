package com.jm.wbc.repository.room

import com.jm.wbc.data.entity.SearchHistoryEntity
import com.jm.wbc.di.coroutine.IoDispatcher
import com.jm.wbc.room.SearchHistoryDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): RoomRepository {
    override fun getAllHistory(): Flow<List<SearchHistoryEntity>> = searchHistoryDao.getAllItem()

    override suspend fun insertHistory(searchHistoryEntity: SearchHistoryEntity) = withContext(ioDispatcher) {
        searchHistoryDao.insertItem(searchHistoryEntity)
    }

    override suspend fun deleteHistory(searchHistoryEntity: SearchHistoryEntity) = withContext(ioDispatcher) {
        searchHistoryDao.deleteItem(searchHistoryEntity)
    }

    override suspend fun deleteAllHistory() {
        searchHistoryDao.deleteAllItem()
    }
}