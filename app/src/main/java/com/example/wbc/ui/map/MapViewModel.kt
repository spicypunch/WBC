package com.example.wbc.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.data.entity.KakaoSearchResponse
import com.example.wbc.repository.KakaoMapRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val kakaoMapRepository: KakaoMapRepositoryImpl
) : ViewModel() {

    private val _searchResult = MutableLiveData<List<KakaoSearchResponse.Document>>()
    val searchResult: LiveData<List<KakaoSearchResponse.Document>>
        get() = _searchResult
    fun searchLocation() {
        viewModelScope.launch {
            _searchResult.value = kakaoMapRepository.searchLocation()
        }
    }
}