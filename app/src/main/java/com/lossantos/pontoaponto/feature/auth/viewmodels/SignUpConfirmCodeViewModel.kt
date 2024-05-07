package com.lossantos.pontoaponto.feature.auth.viewmodels

import androidx.lifecycle.ViewModel
import com.lossantos.pontoaponto.api.PontoAPontoService
import com.lossantos.pontoaponto.api.RetrofitClient
import com.lossantos.pontoaponto.models.request.GenerateNewOtpRequest
import com.lossantos.pontoaponto.models.request.ValidateOtpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpConfirmCodeViewModel : ViewModel() {
    suspend fun validateOtp(validateOtpRequest: ValidateOtpRequest): Boolean? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = RetrofitClient.createRetrofit()
                val service = retrofit?.create(PontoAPontoService::class.java)

                val response = service?.validateOtpCode(validateOtpRequest)

                response?.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun generateNewOtp(generateNewOtpRequest: GenerateNewOtpRequest) : Boolean? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = RetrofitClient.createRetrofit()
                val service = retrofit?.create(PontoAPontoService::class.java)

                val response = service?.generateNewOtp(generateNewOtpRequest)

                response?.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}