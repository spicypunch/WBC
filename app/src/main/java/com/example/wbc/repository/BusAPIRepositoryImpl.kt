package com.example.wbc.repository

import com.example.wbc.data.api.BusService
import com.example.wbc.data.entity.BusStationResponse
import retrofit2.*
import javax.inject.Inject

class BusAPIRepositoryImpl @Inject constructor(
    private val provideBusService: Retrofit
) : BusAPIRepository {

    override suspend fun getBusStationInfo(
        latitude: Double,
        longitude: Double
    ): BusStationResponse {
        return provideBusService.create(BusService::class.java)
            .getBusStationInfo(
                "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
                latitude,
                longitude
            ).await()
    }

    override suspend fun getBusArrivalTime(stationId: String) {

    }
}