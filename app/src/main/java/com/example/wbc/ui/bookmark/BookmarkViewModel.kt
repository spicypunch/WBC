package com.example.wbc.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.data.entity.BookmarkEntity
import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.repository.busapi.BusAPIRepositoryImpl
import com.example.wbc.repository.firebase.FirebaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl,
    private val busAPIRepository: BusAPIRepositoryImpl
) : ViewModel() {

    private val list: MutableList<BookmarkEntity> = mutableListOf()
    private val busList: MutableList<BusArrivalResponse> = mutableListOf()

    private val _busArrivalTimeResult = MutableLiveData<MutableList<BusArrivalResponse>>()
    val busArrivalTimeResult: LiveData<MutableList<BusArrivalResponse>>
        get() = _busArrivalTimeResult

    fun getMyBookmark() {
        viewModelScope.launch {
            list.clear()
            val results = firebaseRepository.getMyBookmark()
            for (result in results) {
                val item = result.toObject(BookmarkEntity::class.java)
                list.add(item)
            }
        }
        getBusArrivalTime()
    }

    private fun getBusArrivalTime() {
        viewModelScope.launch {
            for (i in list) {
                busList.add(busAPIRepository.getBusArrivalTime(i.stationID))
            }
        }
        filterBookmarkBus()
    }

    private fun filterBookmarkBus() {
        for (i in busList) {
            for (j in i.body?.busArrivalList!!) {
//                if (j.routeId == ) {
//
//                }
            }
        }
        _busArrivalTimeResult.value
    }
}