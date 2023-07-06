package com.jm.wbc.data.api

import com.jm.wbc.data.entity.BusArrivalResponse
import com.jm.wbc.data.entity.BusInfoResponse
import com.jm.wbc.data.entity.BusStationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BusService {

    @GET("/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList")
    suspend fun getBusStationInfo(
        @Query("serviceKey") serviceKey: String,
        @Query("gpsLati") gpsLati: Double,
        @Query("gpsLong") gpsLong: Double
    ): BusStationResponse

    @GET("/6410000/busarrivalservice/getBusArrivalList")
    suspend fun getBusArrivalTime(
        @Query("serviceKey") serviceKey: String,
        @Query("stationId") stationId: String
    ): BusArrivalResponse

    @GET("/6410000/busrouteservice/getBusRouteInfoItem")
    suspend fun getBusName(
        @Query("serviceKey") serviceKey: String,
        @Query("routeId") routeId: Int
    ): BusInfoResponse
}