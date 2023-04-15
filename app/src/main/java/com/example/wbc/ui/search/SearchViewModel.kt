package com.example.wbc.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.entity.SearchHistoryEntity
import com.example.wbc.repository.RoomRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val roomRepository: RoomRepositoryImpl
) : ViewModel() {

    private val _items = MutableLiveData<List<SearchHistoryEntity>>(listOf())
    val items: MutableLiveData<List<SearchHistoryEntity>>
        get() = _items

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

    fun deleteHistory() {

    }


}