package com.example.wbc.di.retrofit.kakao

import com.example.wbc.data.api.KakaoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoApiModule {
    private const val BASE_URL = "https://dapi.kakao.com"

    @Singleton
    @Provides
    fun provideKakaoService(): KakaoService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(KakaoService::class.java)
    }
}