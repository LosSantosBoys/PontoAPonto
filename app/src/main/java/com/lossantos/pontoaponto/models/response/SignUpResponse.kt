package com.lossantos.pontoaponto.models.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponse(
    @Json(name = "Message")
    @SerializedName("Message")
    val message: String,
    )
