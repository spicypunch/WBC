package com.example.wbc.di.retrofit.bus

import com.example.wbc.data.api.BusService
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BusAPIModule {
    private const val BASE_URL = "http://apis.data.go.kr"

    @Singleton
    @Provides
    fun provideBusService(): BusService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                )
            ).build().create(BusService::class.java)
    }
}