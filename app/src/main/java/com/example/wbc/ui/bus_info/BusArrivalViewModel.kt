package com.example.wbc.ui.bus_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.repository.BusAPIRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusArrivalViewModel @Inject constructor(
    private val busAPIRepository: BusAPIRepositoryImpl
) : ViewModel(){

    private val _busArrivalTimeResult = MutableLiveData<BusArrivalResponse>()
    val busArrivalTimeResult: LiveData<BusArrivalResponse>
        get() = _busArrivalTimeResult

    fun getBusArrivalTime(stationId: String) {
        viewModelScope.launch {
            _busArrivalTimeResult.value = busAPIRepository.getBusArrivalTime(stationId)
        }
    }
}