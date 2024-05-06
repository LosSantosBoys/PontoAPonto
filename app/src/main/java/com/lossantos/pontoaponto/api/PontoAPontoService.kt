package com.lossantos.pontoaponto.api

import com.lossantos.pontoaponto.models.request.GenerateNewOtpRequest
import com.lossantos.pontoaponto.models.request.SignUpRequest
import com.lossantos.pontoaponto.models.request.ValidateOtpRequest
import com.lossantos.pontoaponto.models.response.BaseResponse
import com.lossantos.pontoaponto.models.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST

interface PontoAPontoService {
    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST("/api/v1/user/signup")
    suspend fun postSignUp(@Body body: SignUpRequest) : Response<BaseResponse<SignUpResponse>>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @PATCH("/api/v1/user/signup/otp/validate")
    suspend fun validateOtpCode(@Body body: ValidateOtpRequest) : Response<Boolean>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @PATCH("/api/v1/user/signup/otp/new")
    suspend fun generateNewOtp(@Body body: GenerateNewOtpRequest) : Response<Boolean>
}