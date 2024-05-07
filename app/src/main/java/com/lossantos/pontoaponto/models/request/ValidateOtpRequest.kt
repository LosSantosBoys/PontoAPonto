package com.lossantos.pontoaponto.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidateOtpRequest (
    @Json(name = "Email")
    val email: String,
    @Json(name = "Otp")
    val otp: Int
)

