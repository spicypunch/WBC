package com.example.wbc.data.api

import com.example.wbc.data.entity.KakaoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoService {
    @GET("/v2/local/search/keyword.json")
    fun searchPlace(
        @Query("query") query: String,
        @Query("radius") radius: Int,
        @Query("y") y: Double,
        @Query("x") x: Double,
        @Header("Authorization") Authorization: String
    ): Call<KakaoResponse>
}