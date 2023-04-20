package com.example.wbc.repository

import com.example.wbc.data.entity.KakaoSearchResponse

interface KakaoMapRepository {

    suspend fun searchLocation(): List<KakaoSearchResponse.Document>
}