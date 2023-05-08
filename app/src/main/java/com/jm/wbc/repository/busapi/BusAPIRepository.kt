package com.jm.wbc.repository.busapi

import com.jm.wbc.data.entity.BusArrivalResponse
import com.jm.wbc.data.entity.BusInfoResponse
import com.jm.wbc.data.entity.BusStationResponse

interface BusAPIRepository {

    suspend fun getBusStationInfo(latitude: Double, longitude: Double): BusStationResponse

    suspend fun getBusArrivalTime(stationId: String): BusArrivalResponse

    suspend fun getBusName(routeId: Int) : BusInfoResponse
}