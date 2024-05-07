package com.lossantos.pontoaponto.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequest (
    @Json(name = "Email")
    val email: String,
    @Json(name = "Password")
    val password: String
)

