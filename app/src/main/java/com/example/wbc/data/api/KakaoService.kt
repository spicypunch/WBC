package com.example.wbc.data.api

import retrofit2.Call
import com.example.wbc.data.entity.KakaoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoService {
    @GET("/v2/local/search/keyword.json?radius=2000")
    suspend fun searchPlace(
        @Query("query") query: String,
        @Header("Authorization") auth: String
    ): Call<List<KakaoSearchResponse.Document>>
}