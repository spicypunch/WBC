package com.jm.wbc.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jm.wbc.data.entity.BusStationResponse
import com.jm.wbc.data.entity.KakaoResponse
import com.jm.wbc.repository.busapi.BusAPIRepositoryImpl
import com.jm.wbc.repository.kakaoapi.KakaoMapRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val kakaoMapRepository: KakaoMapRepositoryImpl,
    private val busAPIRepository: BusAPIRepositoryImpl
) : ViewModel() {

    private val _searchResult = MutableLiveData<KakaoResponse>()
    val searchResult: LiveData<KakaoResponse>
        get() = _searchResult

    private val _busStationResult = MutableLiveData<BusStationResponse>()
    val busStationResult: LiveData<BusStationResponse>
        get() = _busStationResult

    fun searchLocation(address: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                _searchResult.value = kakaoMapRepository.searchLocation(address, latitude, longitude)
            } catch (e: Exception) {
                Log.e("SearchLocationErr", e.toString())
            }
        }
    }

    fun getBusStationInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                _busStationResult.value = busAPIRepository.getBusStationInfo(latitude, longitude)
            } catch (e: Exception) {
                Log.e("GetBusStationInfoErr", e.toString())
            }
        }
    }
}