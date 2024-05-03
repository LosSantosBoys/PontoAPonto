package com.lossantos.pontoaponto.feature.auth.forgotpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.Util
import com.lossantos.pontoaponto.feature.auth.components.AuthComponents

class ForgotPasswordScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        var email by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf(false) }

        Scaffold(topBar = {
            TopAppBar(title = { /*TODO*/ }, navigationIcon = {
                if (navController?.previousBackStackEntry != null) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            }, modifier = Modifier.padding(0.dp))
        }) { it ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 24.dp, vertical = 30.dp)
                    .fillMaxWidth()
            ) {
                Util().Header(
                    title = "Esqueceu a senha?",
                    subtitle = "Por favor, insira seu e-mail ou seu CPF."
                )
                Spacer(modifier = Modifier.height(10.dp))
                Fields(email, { newEmail ->
                    email = newEmail
                    emailError = !isEmailValid(newEmail)
                }, isError = emailError)
                Spacer(modifier = Modifier.weight(1f))
                ForgotPasswordButton()
            }
        }
    }

    @Composable
    fun Fields(
        email: String,
        onEmailChange: (String) -> Unit,
        isError: Boolean,
        modifier: Modifier = Modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            AuthComponents().EmailField(
                email = email,
                onEmailChange = onEmailChange,
                isError = isError,
                allowCPF = true
            )
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @Composable
    fun ForgotPasswordButton(modifier: Modifier = Modifier) {
        Button(
            onClick = {
                // todo: implementar esqueci a senha
            },
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFF3755C1)),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .heightIn(min = 44.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Recuperar senha",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen().Screen()
}