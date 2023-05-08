package com.jm.wbc.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jm.wbc.data.entity.SearchHistoryEntity
import com.jm.wbc.repository.room.RoomRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val roomRepository: RoomRepositoryImpl
) : ViewModel() {

    private val _items = MutableLiveData<List<SearchHistoryEntity>>()
    val items: LiveData<List<SearchHistoryEntity>>
        get() = _items

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun getHistory() {
        viewModelScope.launch {
            roomRepository.getAllHistory().collect() { result ->
                _items.value = result
            }
        }
    }

    fun insertHistory(searchHistory: String) {
        viewModelScope.launch {
            roomRepository.insertHistory(
                SearchHistoryEntity(
                    null,
                    searchHistory
                )
            )
        }
    }

    fun deleteHistory(item: SearchHistoryEntity) {
        viewModelScope.launch {
            roomRepository.deleteHistory(item)
        }
        _toastMessage.value = "검색 기록이 삭제되었습니다."
    }

    fun deleteAllHistory() {
        viewModelScope.launch {
            roomRepository.deleteAllHistory()
        }
        _toastMessage.value = "검색 기록이 전부 삭제되었습니다."
    }
}