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

class SignupConfirmCodeScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        var code by remember { mutableStateOf("") }

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
                AuthComponents().Header(
                    title = "Verificação de segurança",
                    subtitle = "Enviamos um código ao seu e-mail! Dê uma olhada e o insira embaixo para verificar."
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
                }
                Spacer(modifier = Modifier.weight(1f))
                ButtonsComponents().BaseButton("Continuar", { navController?.navigate("login_with_email_and_password") })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupConfirmCodeScreenPreview() {
    SignupConfirmCodeScreen().Screen()
}