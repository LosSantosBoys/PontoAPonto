package com.lossantos.pontoaponto.feature.auth.signup

import com.lossantos.pontoaponto.feature.auth.components.ButtonsComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.auth.components.AuthComponents
import com.lossantos.pontoaponto.feature.auth.components.BarComponents
import com.lossantos.pontoaponto.feature.auth.components.InputComponents

class SignupPersonalDataScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var cpf by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }

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
                AuthComponents().Header(title = "Agora seus dados pessoais!")
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    InputComponents().TextInput(
                        label = "Nome",
                        placeholder = "Nome",
                        value = name,
                        onChange = { name = it }
                    )
                    InputComponents().PhoneInput(
                        label = "Número",
                        placeholder = "Número",
                        value = phone,
                        onChange = { phone = it }
                    )
                    InputComponents().CpfInput(
                        label = "Cpf",
                        placeholder = "Cpf",
                        value = cpf,
                        onChange = { cpf = it }
                    )
                    InputComponents().DateInput(
                        label = "Data de nascimento",
                        placeholder = "Data de nascimento",
                        value = birthDate,
                        onChange = { birthDate = it }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                ButtonsComponents().BaseButton("Continuar", { navController?.navigate("login_with_email_and_password") })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPersonalDataScreenPreview() {
    SignupPersonalDataScreen().Screen()
}