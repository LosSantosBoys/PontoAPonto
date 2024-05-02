package com.lossantos.pontoaponto.models.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("Name") val name: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Phone") val phone: String,
    @SerializedName("Password") val password: String,
    @SerializedName("Cpf") val cpf: String,
    @SerializedName("Birthday") val birthday: String
)

