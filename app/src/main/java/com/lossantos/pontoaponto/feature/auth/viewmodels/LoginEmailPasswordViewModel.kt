package com.lossantos.pontoaponto.feature.auth.viewmodels

import androidx.lifecycle.ViewModel
import com.lossantos.pontoaponto.api.PontoAPontoService
import com.lossantos.pontoaponto.api.RetrofitClient
import com.lossantos.pontoaponto.models.request.SignInRequest
import com.lossantos.pontoaponto.models.response.BaseResponse
import com.lossantos.pontoaponto.models.response.SignInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginEmailPasswordViewModel : ViewModel() {
    suspend fun signIn(signInRequest: SignInRequest) : BaseResponse<SignInResponse>? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = RetrofitClient.createRetrofit()
                val service = retrofit?.create(PontoAPontoService::class.java)

                val response = service?.signIn(signInRequest)

                response?.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}