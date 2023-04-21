package com.example.wbc.repository

import android.util.Log
import com.example.wbc.data.api.KakaoService
import com.example.wbc.data.entity.KakaoSearchResponse
import retrofit2.*
import javax.inject.Inject

class KakaoMapRepositoryImpl @Inject constructor(
    private val provideKakaoService: Retrofit
) : KakaoMapRepository {
    override fun searchLocation() {
        val response = provideKakaoService.create(KakaoService::class.java)
        response.searchPlace(
            "성남시청",
            "KakaoAK adbab23df5d7301de7db6ce10d19ff10")
            .enqueue(object : Callback<List<KakaoSearchResponse.Document>> {
                override fun onResponse(
                    call: Call<List<KakaoSearchResponse.Document>>,
                    response: Response<List<KakaoSearchResponse.Document>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.e("it", it.toString())
                        }
                    } else {
                        Log.e("code", response.code().toString())
                    }
                }

                override fun onFailure(
                    call: Call<List<KakaoSearchResponse.Document>>,
                    t: Throwable
                ) {
                    Log.e("fail", t.message!!)
                    t.printStackTrace()
                }

            })
    }
}