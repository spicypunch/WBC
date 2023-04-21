package com.example.wbc.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.data.entity.KakaoResponse
import com.example.wbc.repository.KakaoMapRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val kakaoMapRepository: KakaoMapRepositoryImpl
) : ViewModel() {

    private val _searchResult = MutableLiveData<KakaoResponse>()
    val searchResult: LiveData<KakaoResponse>
        get() = _searchResult

    fun searchLocation(address: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _searchResult.value = kakaoMapRepository.searchLocation(address, latitude, longitude)
        }
    }
}