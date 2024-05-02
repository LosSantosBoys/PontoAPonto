package com.lossantos.pontoaponto.service

import com.lossantos.pontoaponto.api.RetrofitClient
import com.lossantos.pontoaponto.models.request.SignUpRequest
import com.lossantos.pontoaponto.models.response.BaseResponse
import com.lossantos.pontoaponto.models.response.SignUpResponse

class UserService {
    suspend fun createUser(signUpRequest: SignUpRequest): BaseResponse<SignUpResponse>? {
        val apiService = RetrofitClient.createPontoAPonto()

        return try {
            val response = apiService.createUserSignUp(signUpRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                // Lidar com erros de resposta
                null
            }
        } catch (e: Exception) {
            // Lidar com erros de rede
            null
        }
    }
}
