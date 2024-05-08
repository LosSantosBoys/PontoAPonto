package com.lossantos.pontoaponto.feature.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.Util
import com.lossantos.pontoaponto.feature.auth.components.AuthComponents
import com.lossantos.pontoaponto.feature.auth.components.BarComponents
import com.lossantos.pontoaponto.feature.auth.components.ButtonsComponents
import com.lossantos.pontoaponto.feature.auth.components.InputComponents
import com.lossantos.pontoaponto.feature.auth.viewmodels.SignUpPersonalViewModel
import com.lossantos.pontoaponto.models.SignUpViewModel
import com.lossantos.pontoaponto.models.request.SignUpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class SignupPersonalDataScreen(private val navController: NavController, private val viewModel: SignUpViewModel) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var cpf by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }

        var isNameValid by remember { mutableStateOf(true) }
        var isPhoneValid by remember { mutableStateOf(true) }
        var isCpfValid by remember { mutableStateOf(true) }
        var isBirthDateValid by remember { mutableStateOf(true) }

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
                Util().Header(title = "Agora seus dados pessoais!")
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    InputComponents().TextInput(
                        label = "Nome Completo",
                        placeholder = "Nome Completo",
                        value = name,
                        onChange = { newName ->
                            name = newName
                            isNameValid = newName.isNotBlank()
                        },
                        isError = !isNameValid
                    )
                    InputComponents().PhoneInput(
                        label = "Número de celular",
                        placeholder = "Número de celular",
                        value = phone,
                        onChange = { newPhone ->
                            phone = newPhone
                            isPhoneValid = newPhone.length == 11
                        },
                        isError = !isPhoneValid
                    )
                    InputComponents().CpfInput(
                        label = "CPF",
                        placeholder = "CPF",
                        value = cpf,
                        onChange = { newCpf ->
                            cpf = newCpf
                            isCpfValid = newCpf.length == 11
                        },
                        isError = !isCpfValid
                    )
                    InputComponents().DateInput(
                        label = "Data de nascimento",
                        placeholder = "Data de nascimento",
                        value = birthDate,
                        onChange = { newBirthDate ->
                            birthDate = newBirthDate
                            isBirthDateValid = isValidDate(newBirthDate)
                        },
                        isError = !isBirthDateValid
                    )
                }

                val signUpRequest = SignUpRequest(
                    name = name,
                    email = viewModel.email,
                    phone = phone,
                    password = viewModel.password,
                    cpf = cpf,
                    birthday = birthDate
                )

                Spacer(modifier = Modifier.weight(1f))
                ButtonsComponents().BaseButton(
                    "Continuar",
                    onClick = {
                            isLoading = true
                            CoroutineScope(Dispatchers.IO).launch {
                                val success = createUser(signUpRequest)
                                isLoading = false

                                withContext(Dispatchers.Main) {
                                    if (success) {
                                        navController.navigate("signup_confirm_code")
                                    } else {
                                        //Snackbar
                                    }
                                }
                            }
                    },
                    enabled = !isLoading && name.isNotBlank() && phone.length == 11 && cpf.length == 11 && isValidDate(birthDate)
                )
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = Color.Blue
                    )
                }
            }
        }
    }
}

fun isValidDate(date: String): Boolean {
    val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    dateFormat.isLenient = false

    return try {
        val parsedDate = dateFormat.parse(date)
        val calendar = Calendar.getInstance().apply { set(Calendar.YEAR, 1900) }
        val earliestDate = calendar.time
        val currentDate = Date()

        parsedDate in earliestDate..currentDate
    } catch (e: Exception) {
        false
    }
}

suspend fun createUser(request: SignUpRequest) : Boolean {
    val signUpPersonalViewModel = SignUpPersonalViewModel()
    val response = signUpPersonalViewModel.postSignUp(request)
    return response?.success ?: false
}

@Preview(showBackground = true)
@Composable
fun SignupPersonalDataScreenPreview() {
    val mockViewModel = SignUpViewModel()
    mockViewModel.email = "teste@email.com"
    mockViewModel.password = "1234senha"
    SignupPersonalDataScreen(navController = NavController(context = LocalContext.current), viewModel = mockViewModel).Screen()
}