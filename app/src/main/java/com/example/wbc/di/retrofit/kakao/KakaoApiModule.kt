package com.example.wbc.di.retrofit.kakao

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoApiModule {
    private const val BASE_URL = "https://dapi.kakao.com"
    private var INSTANCE: Retrofit? = null

    @Singleton
    @Provides
    fun provideKakaoService(): Retrofit {
        if (INSTANCE == null) {
            INSTANCE = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return INSTANCE!!
    }
}