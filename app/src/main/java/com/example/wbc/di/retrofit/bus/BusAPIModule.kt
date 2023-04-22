package com.example.wbc.di.retrofit.bus

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BusAPIModule {
    private const val BASE_URL = "http://apis.data.go.kr"
    private var INSTANCE: Retrofit? = null

    @Singleton
    @Provides
    fun provideBusService(): Retrofit {
        if (INSTANCE == null) {
            INSTANCE = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(
                    TikXmlConverterFactory.create(
                        TikXml.Builder().exceptionOnUnreadXml(false).build()
                    )
                ).build()
        }
        return INSTANCE!!
    }

}