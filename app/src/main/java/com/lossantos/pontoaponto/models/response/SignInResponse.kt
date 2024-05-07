package com.lossantos.pontoaponto.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInResponse (
    @Json(name = "TokenType")
    val tokenType: String,
    @Json(name = "Token")
    val token: String,
    @Json(name = "IsFirstAccess")
    val isFirstAccess: Boolean
)