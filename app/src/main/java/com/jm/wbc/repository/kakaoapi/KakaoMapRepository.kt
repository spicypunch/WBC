package com.jm.wbc.repository.kakaoapi

import com.jm.wbc.data.entity.KakaoResponse


interface KakaoMapRepository {

    suspend fun searchLocation(address: String, latitude: Double, longitude: Double) : KakaoResponse
}