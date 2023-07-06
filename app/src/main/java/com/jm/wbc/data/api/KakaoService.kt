package com.jm.wbc.data.api

import com.jm.wbc.data.entity.KakaoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoService {
    @GET("/v2/local/search/keyword.json")
    suspend fun searchPlace(
        @Query("query") query: String,
        @Query("radius") radius: Int,
        @Query("y") y: Double,
        @Query("x") x: Double,
        @Header("Authorization") Authorization: String
    ): KakaoResponse
}