package com.lossantos.pontoaponto.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponse(
    @Json(name = "Message")
    val message: String,
    )
