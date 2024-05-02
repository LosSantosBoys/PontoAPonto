package com.lossantos.pontoaponto.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://localhost:5001/"

    fun createPontoAPonto(): PontoAPontoService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(PontoAPontoService::class.java)
    }
}