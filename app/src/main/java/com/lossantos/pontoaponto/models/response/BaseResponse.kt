package com.lossantos.pontoaponto.models.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("StatusCode") val statusCode: Int,
    @SerializedName("Message") val message: String,
    @SerializedName("Success") val success: Boolean,
    @SerializedName("Data") val data: T? = null
) {}