package com.lossantos.pontoaponto.feature.auth.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lossantos.pontoaponto.api.PontoAPontoService
import com.lossantos.pontoaponto.api.RetrofitClient
import com.lossantos.pontoaponto.models.request.SignUpRequest
import com.lossantos.pontoaponto.models.response.BaseResponse
import com.lossantos.pontoaponto.models.response.SignUpResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpPersonalViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    suspend fun postSignUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitClient.createRetrofit()
                val service = retrofit?.create(PontoAPontoService::class.java)

                val requestBodyString = Gson().toJson(signUpRequest)
                log("SignUp Request Body: $requestBodyString")

                val call = service?.postSignUp(signUpRequest)
                call?.enqueue(object : Callback<BaseResponse<SignUpResponse>> {
                    override fun onResponse(
                        call: Call<BaseResponse<SignUpResponse>>,
                        response: Response<BaseResponse<SignUpResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                val message = responseBody.message
                                _message.value = message

                                val responseBodyString = Gson().toJson(responseBody)
                                log("SignUp Response Body: $responseBodyString")
                            }
                        } else {
                            val errorBody = response.errorBody()?.string()
                            log("SignUp Error: ${response.code()} - $errorBody")
                            _message.value = "Erro ao processar a solicitação"
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<SignUpResponse>>, t: Throwable) {
                        log("SignUp Call Failure: ${t.message}")
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
                _message.value = "Erro de conexão. Verifique sua conexão com a internet."
            }
        }
    }

    private fun log(message: String) {
        Log.d("SignUpViewModel", message)
    }
}
