package com.lossantos.pontoaponto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL =
        "http://localhost:5000" //Need to use your ipv4 or proxy :5000

    fun createRetrofit() : Retrofit? {
        Retrofit.Builder()
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}