package com.jm.wbc.repository.kakaoapi

import com.jm.wbc.data.api.KakaoService
import com.jm.wbc.data.entity.KakaoResponse
import retrofit2.*
import javax.inject.Inject

class KakaoMapRepositoryImpl @Inject constructor(
    private val kakaoService: KakaoService
) : KakaoMapRepository {
    override suspend fun searchLocation(
        address: String,
        latitude: Double,
        longitude: Double
    ): KakaoResponse {
        return kakaoService.searchPlace(
                address,
                5000,
                latitude,
                longitude,
                "KakaoAK adbab23df5d7301de7db6ce10d19ff10"
            ).await()
    }
}