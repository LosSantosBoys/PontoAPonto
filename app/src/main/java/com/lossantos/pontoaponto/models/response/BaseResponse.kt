package com.lossantos.pontoaponto.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "StatusCode")
    val statusCode: Int,
    @Json(name = "Message")
    val message: String,
    @Json(name = "Success")
    val success: Boolean,
    @Json(name = "Data")
    val data: T? = null
) {}