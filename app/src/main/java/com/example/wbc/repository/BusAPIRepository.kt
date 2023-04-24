package com.example.wbc.repository

import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.data.entity.BusStationResponse

interface BusAPIRepository {

    suspend fun getBusStationInfo(latitude: Double, longitude: Double): BusStationResponse

    suspend fun getBusArrivalTime(stationId: String): BusArrivalResponse
}