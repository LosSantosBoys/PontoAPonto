package com.lossantos.pontoaponto.feature.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

class AuthComponents {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EmailField(email: String, onEmailChange: (String) -> Unit, isError: Boolean, modifier: Modifier = Modifier, allowCPF: Boolean = false) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            val fieldLabel = if (allowCPF) "E-mail ou CPF" else "E-mail"

            InputComponents().TextInput(
                fieldLabel,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            TextField(
                value = email,
                onValueChange = onEmailChange,
                label = {
                    Text(
                        if (isError) "E-mail inválido" else fieldLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isError) Color(0xFFE30000) else Color(0xFF7D7D7D)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = if (isError) Color(0xFFFFF2F4) else Color(0xFFFAFAFA),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                modifier = modifier.fillMaxWidth()
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PasswordField(modifier: Modifier = Modifier) {
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Senha",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        "Senha",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF7D7D7D)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFFAFAFA),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                trailingIcon = {
                    val image =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Esconder a senha" else "Mostrar senha"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description, modifier = modifier.size(20.dp))
                    }
                },
                modifier = modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    fun Header(title: String, modifier: Modifier = Modifier, subtitle: String? = null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                title,
                style = MaterialTheme.typography.labelLarge,
                modifier = modifier
            )

            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier
                )
            }
        }
    }
}