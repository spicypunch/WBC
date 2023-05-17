package com.jm.wbc.repository.busapi

import com.jm.wbc.data.api.BusService
import com.jm.wbc.data.entity.BusArrivalResponse
import com.jm.wbc.data.entity.BusInfoResponse
import com.jm.wbc.data.entity.BusStationResponse
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
        )
    }

    override suspend fun getBusArrivalTime(stationId: String): BusArrivalResponse {
        return busService.getBusArrivalTime(
            "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
            stationId
        )
    }

    override suspend fun getBusName(routeId: Int): BusInfoResponse {
        return busService.getBusName(
            "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
            routeId
        )
    }
}