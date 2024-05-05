package com.lossantos.pontoaponto.api

import com.google.gson.GsonBuilder
import com.lossantos.pontoaponto.utils.masker.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL =
        "http://localhost:5000" //Need to use your ipv4 or proxy :5000

    val client = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .build()

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun createRetrofit() : Retrofit? {
        Retrofit.Builder()
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}