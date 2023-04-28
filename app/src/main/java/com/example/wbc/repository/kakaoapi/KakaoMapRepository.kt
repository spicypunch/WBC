package com.example.wbc.repository.kakaoapi

import com.example.wbc.data.entity.KakaoResponse


interface KakaoMapRepository {

    suspend fun searchLocation(address: String, latitude: Double, longitude: Double) : KakaoResponse
}