package com.lossantos.pontoaponto.feature.auth.viewmodels

import androidx.lifecycle.ViewModel
import com.lossantos.pontoaponto.api.PontoAPontoService
import com.lossantos.pontoaponto.api.RetrofitClient
import com.lossantos.pontoaponto.models.request.SignUpRequest
import com.lossantos.pontoaponto.models.response.BaseResponse
import com.lossantos.pontoaponto.models.response.SignUpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpPersonalViewModel : ViewModel() {

    suspend fun postSignUp(signUpRequest: SignUpRequest): BaseResponse<SignUpResponse>? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = RetrofitClient.createRetrofit()
                val service = retrofit?.create(PontoAPontoService::class.java)

                val response = service?.postSignUp(signUpRequest)

                response?.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}