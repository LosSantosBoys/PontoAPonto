package com.lossantos.pontoaponto.models.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "StatusCode")
    @SerializedName("StatusCode")
    val statusCode: Int,
    @Json(name = "Message")
    @SerializedName("Message")
    val message: String,
    @Json(name = "Success")
    @SerializedName("Success")
    val success: Boolean,
    @Json(name = "Data")
    @SerializedName("Data")
    val data: T? = null
) {}