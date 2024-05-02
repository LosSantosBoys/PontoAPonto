package com.lossantos.pontoaponto.models.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("Message") val message: String,
    )
