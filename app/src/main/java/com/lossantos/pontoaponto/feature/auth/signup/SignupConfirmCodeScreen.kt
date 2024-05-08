package com.lossantos.pontoaponto.feature.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.Util
import com.lossantos.pontoaponto.feature.auth.components.AuthComponents
import com.lossantos.pontoaponto.feature.auth.components.BarComponents
import com.lossantos.pontoaponto.feature.auth.components.ButtonsComponents
import com.lossantos.pontoaponto.feature.auth.components.InputComponents
import com.lossantos.pontoaponto.feature.auth.viewmodels.SignUpConfirmCodeViewModel
import com.lossantos.pontoaponto.models.SignUpViewModel
import com.lossantos.pontoaponto.models.request.GenerateNewOtpRequest
import com.lossantos.pontoaponto.models.request.ValidateOtpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupConfirmCodeScreen(private val navController: NavController,  private val viewModel: SignUpViewModel) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        var code by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }

        Scaffold(topBar = { BarComponents().AppBar(navController) }) { it ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 24.dp, vertical = 30.dp)
                    .fillMaxWidth()
            )
            {
                Util().Header(
                    title = "Verificação de segurança",
                    subtitle = "Enviamos um código ao seu e-mail! Dê uma olhada e insira-o abaixo para verificação."
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    InputComponents().TextInput(
                        label = null,
                        placeholder = "Código",
                        value = code,
                        onChange = { code = it }
                    )

                    GenerateNewOtp(email = viewModel.email)
                }

                Spacer(modifier = Modifier.weight(1f))
                ButtonsComponents().BaseButton("Continuar",
                    onClick =    {
                        CoroutineScope(Dispatchers.IO).launch {
                            val success = validateOtp(viewModel.email, code)
                            withContext(Dispatchers.Main) {
                                if (success) {
                                    navController.navigate("login_with_email_and_password")
                                } else {
                                    //Snackbar
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GenerateNewOtp(modifier: Modifier = Modifier, email: String) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.labelSmall.toSpanStyle()) {
            append("Precisa de um novo código? ")
        }

        pushStringAnnotation(tag = "Clickable", annotation = "Gere um aqui!")
        withStyle(
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                .toSpanStyle()
        ) {
            append("Gere um aqui!")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "Clickable",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    val request = GenerateNewOtpRequest(email)
                    generateNewOtp(request)
                }
            }
        })
}

suspend fun validateOtp(email: String, code: String) : Boolean {
    val request = ValidateOtpRequest(
        email = email,
        otp = code.toInt()
    )

    val signUpConfirmCodeViewModel = SignUpConfirmCodeViewModel()
    val response = signUpConfirmCodeViewModel.validateOtp(request)
    return response ?: false
}

suspend fun generateNewOtp(request: GenerateNewOtpRequest) : Boolean {
    val signUpConfirmCodeViewModel = SignUpConfirmCodeViewModel()
    val response = signUpConfirmCodeViewModel.generateNewOtp(request)
    return response?: false
}

@Preview(showBackground = true)
@Composable
fun SignupConfirmCodeScreenPreview() {
    val mockViewModel = SignUpViewModel()
    mockViewModel.email = "teste@email.com"
    mockViewModel.password = "1234senha"
    SignupConfirmCodeScreen(navController = NavController(context = LocalContext.current), viewModel = mockViewModel).Screen()
}