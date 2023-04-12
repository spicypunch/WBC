package com.example.wbc.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.repository.RoomRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val roomRepository: RoomRepositoryImpl
) : ViewModel() {

    fun getHistory() {
        viewModelScope.launch {
            roomRepository.getAllHistory()
        }
    }

    fun insertHistory() {

    }

    fun deleteHistory() {

    }


}