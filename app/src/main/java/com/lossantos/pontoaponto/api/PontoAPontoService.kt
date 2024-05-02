package com.lossantos.pontoaponto.api

import com.lossantos.pontoaponto.models.request.SignUpRequest
import com.lossantos.pontoaponto.models.response.BaseResponse
import com.lossantos.pontoaponto.models.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PontoAPontoService {

    @POST("signup")
    suspend fun createUserSignUp(@Body request: SignUpRequest): Response<BaseResponse<SignUpResponse>>
}