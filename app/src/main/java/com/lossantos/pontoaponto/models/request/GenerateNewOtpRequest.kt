package com.lossantos.pontoaponto.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerateNewOtpRequest(
    @Json(name = "Email")
    val email: String,
)



