package com.lossantos.pontoaponto.models.request

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "Name")
    @SerializedName("Name")
    val name: String,
    @Json(name = "Email")
    @SerializedName("Email")
    val email: String,
    @Json(name = "Phone")
    @SerializedName("Phone")
    val phone: String,
    @Json(name = "Password")
    @SerializedName("Password")
    val password: String,
    @Json(name = "Cpf")
    @SerializedName("Cpf")
    val cpf: String,
    @Json(name = "Birthday")
    @SerializedName("Birthday")
    val birthday: String
)

