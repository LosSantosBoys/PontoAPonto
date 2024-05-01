package com.lossantos.pontoaponto.feature.auth.login

import android.widget.CheckBox
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.auth.components.AuthComponents
import com.lossantos.pontoaponto.feature.auth.components.BarComponents
import com.lossantos.pontoaponto.feature.auth.components.ButtonsComponents
import com.lossantos.pontoaponto.feature.auth.components.InputComponents

class LoginWithEmailAndPasswordScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        var email by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf(false) }

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
                    title = "Bem-vindo novamente!",
                    subtitle = "Por favor, insira seu e-mail e senha para entrar."
                )
                Spacer(modifier = Modifier.height(10.dp))
                Fields(email, { newEmail ->
                    email = newEmail
                    emailError = !isEmailValid(newEmail)
                }, isError = emailError)
                Spacer(modifier = Modifier.weight(1f))
                Login()
            }
        }
    }

    @Composable
    fun RememberMeField(modifier: Modifier = Modifier) {
        var rememberMe by remember { mutableStateOf(false) }

        val annotatedText = buildAnnotatedString {
            pushStringAnnotation(tag = "Clickable", annotation = "Esqueceu a senha?")
            withStyle(
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                    .toSpanStyle()
            ) {
                append("Esqueceu a senha?")
            }
            pop()
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                InputComponents().Checkbox(
                    onChange = { rememberMe = it },
                    checked = rememberMe,
                    text = "Lembre-se de mim"
                )
            }
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "Clickable",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        navController?.navigate("forgot_password")
                    }
                }
            )
        }
    }

    @Composable
    fun FieldDivider(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Divider(
                thickness = 1.dp,
                color = Color(0xFFcccccc),
                modifier = modifier.padding(horizontal = 24.dp)
            )
        }
    }

    @Composable
    fun CreateAccount(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            val annotatedText = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.labelSmall.toSpanStyle()) {
                    append("Não tem uma conta? ")
                }

                pushStringAnnotation(tag = "Clickable", annotation = "Crie uma.")
                withStyle(
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        .toSpanStyle()
                ) {
                    append("Crie uma.")
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
                        println("Clicou em ${it.item}")

                        // todo: redirecionar para página de cadastro
                    }
                })
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
            AuthComponents().PasswordField()
            RememberMeField()
            FieldDivider()
            CreateAccount()
        }
    }

    @Composable
    fun Login(modifier: Modifier = Modifier) {
        Button(
            onClick = { navController?.navigate("login_with_email_and_password") },
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFF3755C1)),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .heightIn(min = 44.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Login",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            )
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginWithEmailAndPasswordPreview() {
    LoginWithEmailAndPasswordScreen().Screen()
}