package com.example.wbc.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.data.entity.SearchHistoryEntity
import com.example.wbc.repository.RoomRepositoryImpl
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
            roomRepository.insertHistory(SearchHistoryEntity(null, searchHistory))
        }
    }

    fun deleteHistory(item: SearchHistoryEntity) {
        viewModelScope.launch {
            roomRepository.deleteHistory(item)
        }
        _toastMessage.value = "검색 기록이 삭제되었습니다."
    }


}