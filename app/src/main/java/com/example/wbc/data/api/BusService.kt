package com.example.wbc.data.api

import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.data.entity.BusInfoResponse
import com.example.wbc.data.entity.BusStationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusService {

    @GET("/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList")
    fun getBusStationInfo(
        @Query("serviceKey") serviceKey: String,
        @Query("gpsLati") gpsLati: Double,
        @Query("gpsLong") gpsLong: Double
    ): Call<BusStationResponse>

    @GET("/6410000/busarrivalservice/getBusArrivalList")
    fun getBusArrivalTime(
        @Query("serviceKey") serviceKey: String,
        @Query("stationId") stationId: String
    ): Call<BusArrivalResponse>

    @GET("/6410000/busrouteservice/getBusRouteInfoItem")
    fun getBusName(
        @Query("serviceKey") serviceKey: String,
        @Query("stationId") stationId: String
    ): Call<BusInfoResponse>
}