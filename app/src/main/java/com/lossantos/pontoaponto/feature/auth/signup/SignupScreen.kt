package com.lossantos.pontoaponto.feature.auth.signup

import com.lossantos.pontoaponto.feature.auth.components.ButtonsComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.auth.components.AuthComponents
import com.lossantos.pontoaponto.feature.auth.components.BarComponents
import com.lossantos.pontoaponto.feature.auth.components.InputComponents

class SignupScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
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
                    title = "Crie uma conta",
                    subtitle = "O PontoAPonto é um dos melhores aplicativos de transporte atualmente!"
                )
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AuthComponents().EmailField()
                    AuthComponents().PasswordField()
                    ConfirmPolicyField()
                    Line()
                    Link()
                }
                Spacer(modifier = Modifier.weight(1f))
                ButtonsComponents().BaseButton("Criar conta", { navController?.navigate("signup_personal_data") })
            }
        }
    }

    @Composable
    fun ConfirmPolicyField(modifier: Modifier = Modifier) {
        var rememberMe by remember { mutableStateOf(false) }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                InputComponents().Checkbox(
                    onChange = { rememberMe = it },
                    checked = rememberMe,
                    text = "Concordo com a política de privacidade? "
                )
            }
        }
    }

    @Composable
    fun Link() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val annotatedText = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.labelSmall.toSpanStyle()) {
                    append("Já tem uma conta? ")
                }

                pushStringAnnotation(tag = "Clickable", annotation = "Já tem uma conta? Faça login.")
                withStyle(
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                        .toSpanStyle()
                ) {
                    append("Faça login.")
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
                        navController?.navigate("login_with_email_and_password")
                    }
                }
            )
        }
    }

    @Composable
    fun Line() {
        Column(modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()) {
            Divider(
                thickness = 1.dp,
                color = Color(0xFFcccccc),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen().Screen()
}