package com.example.wbc.repository

import com.example.wbc.data.api.BusService
import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.data.entity.BusInfoResponse
import com.example.wbc.data.entity.BusStationResponse
import retrofit2.*
import javax.inject.Inject

class BusAPIRepositoryImpl @Inject constructor(
    private val busService: BusService
) : BusAPIRepository {

    override suspend fun getBusStationInfo(
        latitude: Double,
        longitude: Double
    ): BusStationResponse {
        return busService.getBusStationInfo(
                "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
                latitude,
                longitude
            ).await()
    }

    override suspend fun getBusArrivalTime(stationId: String): BusArrivalResponse {
        return busService.getBusArrivalTime(
            "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
            stationId
        ).await()
    }

    override suspend fun getBusName(stationId: String): BusInfoResponse {
        return busService.getBusName(
            "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
            stationId
        ).await()
    }
}