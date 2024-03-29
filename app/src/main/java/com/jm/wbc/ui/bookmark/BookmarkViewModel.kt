package com.jm.wbc.ui.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jm.wbc.data.entity.BookmarkEntity
import com.jm.wbc.data.entity.BusArrivalResponse
import com.jm.wbc.data.entity.BusInfoEntity
import com.jm.wbc.repository.busapi.BusAPIRepositoryImpl
import com.jm.wbc.repository.firebase.FirebaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl,
    private val busAPIRepository: BusAPIRepositoryImpl
) : ViewModel() {

    private val firebaseList: MutableList<BookmarkEntity> = mutableListOf()
    private val busArrivalList: MutableList<BusArrivalResponse> = mutableListOf()
    private val busInfoList: MutableList<BusInfoEntity> = mutableListOf()

    private val _busArrivalTimeResult = MutableLiveData<List<BusInfoEntity>>()
    val busArrivalTimeResult: LiveData<List<BusInfoEntity>>
        get() = _busArrivalTimeResult

    private val _deleteResult = MutableLiveData<Boolean>()
    val deleteResult: LiveData<Boolean>
        get() = _deleteResult

    fun getMyBookmark() {
        viewModelScope.launch {
            try {
                firebaseList.clear()
                val results = firebaseRepository.getMyBookmark()
                for (result in results) {
                    val item = result.toObject(BookmarkEntity::class.java)
                    firebaseList.add(item)
                }
                getBusArrivalTime()
            } catch (e: Exception) {
                Log.e("GetMyBookmarkErr", e.toString())
            }
        }
    }

    fun deleteBookmark(busNm: String) {
        viewModelScope.launch {
            try {
                _deleteResult.value = firebaseRepository.deleteBookmark(busNm)
            } catch (e: Exception) {
                Log.e("DeleteBookmarkErr", e.toString())
            }
        }
    }

    private fun getBusArrivalTime() {
        viewModelScope.launch {
            try {
                busArrivalList.clear()
                for (i in firebaseList) {
                    busArrivalList.add(busAPIRepository.getBusArrivalTime(i.stationID))
                }
                filterBookmarkBus()
            } catch (e: Exception) {
                Log.e("GetBusArrivalTimeErr", e.toString())
            }
        }
    }

    /**
     * firebase에서 가져온 정류장 주소로 해당 정류장의 모든 버스 도착 정보를 받아온다(해당 API는 특정 버스를 조회할 수 없고 전체 조회만 지원하기 때문)
     * 받아온 버스 도착 예정 API와 Firebase에 저장했던 정보를 기반으로 내가 즐겨찾기 해두었던 버스 도착 정보를 찾음
     */
    private fun filterBookmarkBus() {
        busInfoList.clear()
        for (i in busArrivalList) {
            for (j in i.body?.busArrivalList!!) {
                for (k in firebaseList) {
                    if (k.routeID == j.routeId.toString() && k.stationID == j.stationId.toString()) {
                        busInfoList.add(
                            BusInfoEntity(
                                busNum = k.routeNm,
                                predictTime1 = j.predictTime1,
                                predictTime2 = j.predictTime2
                            )
                        )
                    }
                }
            }
        }
        _busArrivalTimeResult.value = busInfoList.distinct()
    }
}