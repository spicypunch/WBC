package com.example.wbc.repository

import com.example.wbc.data.api.KakaoService
import com.example.wbc.data.entity.KakaoSearchResponse
import retrofit2.*
import javax.inject.Inject

class KakaoMapRepositoryImpl @Inject constructor(
    private val provideKakaoService: Retrofit
) : KakaoMapRepository {
    override suspend fun searchLocation(): List<KakaoSearchResponse.Document> {
        return try {
            val response = provideKakaoService.create(KakaoService::class.java)
                .searchPlace(
                    "성남시청",
                    "KakaoAK adbab23df5d7301de7db6ce10d19ff10"
                ).await()

            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }

    }

}