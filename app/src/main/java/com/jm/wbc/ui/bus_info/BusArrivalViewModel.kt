package com.jm.wbc.ui.bus_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jm.wbc.data.entity.BusArrivalResponse
import com.jm.wbc.data.entity.BusInfoResponse
import com.jm.wbc.repository.busapi.BusAPIRepositoryImpl
import com.jm.wbc.repository.firebase.FirebaseRepositoryImpl
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
            try {
                _busArrivalTimeResult.value = busAPIRepository.getBusArrivalTime(stationId)
            } catch (e: Exception) {
                Log.e("GetBusArrivalTimeErr", e.toString())
            }
        }
    }

    fun getBusName(routeId: Int) {
        viewModelScope.launch {
            try {
                _busName.value = busAPIRepository.getBusName(routeId)
            } catch (e: Exception) {
                Log.e("GetBusNameErr", e.toString())
            }
        }
    }

    fun insertMyBookmark(stationID: String, routeID: String, routeNm: String) {
        viewModelScope.launch {
            try {
                _insertResult.value = firebaseRepository.insertMyBookmark(stationID, routeID, routeNm)
            } catch (e: Exception) {
                Log.e("InsertBookmarkErr", e.toString())
            }
        }
    }
}