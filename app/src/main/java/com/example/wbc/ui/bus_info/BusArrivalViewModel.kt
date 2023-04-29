package com.example.wbc.ui.bus_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.data.entity.BusInfoResponse
import com.example.wbc.repository.busapi.BusAPIRepositoryImpl
import com.example.wbc.repository.firebase.FirebaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusArrivalViewModel @Inject constructor(
    private val busAPIRepository: BusAPIRepositoryImpl,
    private val firebaseRepository: FirebaseRepositoryImpl
) : ViewModel(){

    private val _busArrivalTimeResult = MutableLiveData<BusArrivalResponse>()
    val busArrivalTimeResult: LiveData<BusArrivalResponse>
        get() = _busArrivalTimeResult

    private val _busName = MutableLiveData<BusInfoResponse>()
    val busName: LiveData<BusInfoResponse>
        get() = _busName

    private val _insertResult = MutableLiveData<Boolean>()
    val insertResult: LiveData<Boolean>
        get() = _insertResult

    fun getBusArrivalTime(stationId: String) {
        viewModelScope.launch {
            _busArrivalTimeResult.value = busAPIRepository.getBusArrivalTime(stationId)
        }
    }

    fun getBusName(routeId: Int) {
        viewModelScope.launch {
            _busName.value = busAPIRepository.getBusName(routeId)
        }
    }

    fun insertMyBookmark(stationID: String, routeID: String, routeNm: String) {
        viewModelScope.launch {
            _insertResult.value = firebaseRepository.insertMyBookmark(stationID, routeID, routeNm)
        }
    }
}