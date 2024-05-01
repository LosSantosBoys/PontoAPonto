package com.lossantos.pontoaponto.feature.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.R

class LoginScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        Scaffold { it ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 24.dp, vertical = 30.dp)
            )
            {
                Spacer(modifier = Modifier.weight(1f))
                AppNameWithImage()
                Spacer(modifier = Modifier.weight(1f))
                Buttons()
                Footer()
            }
        }
    }

    @Composable
    fun Buttons(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterVertically
            ), horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(10.dp)
        ) {
            LoginWithGoogle()
            LoginWithMeta()
            Login()
        }
    }

    @Composable
    fun Footer(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(10.dp)
        ) {
            CreateAccountLink()
        }
    }

    @Composable
    fun LogoImage(modifier: Modifier = Modifier) {
        val image = painterResource(R.drawable.logo)
        Image(
            painter = image,
            contentDescription = "Logo do aplicativo PontoAPonto",
            modifier = modifier.size(width = 212.dp, height = 212.dp)
        )
    }

    @Composable
    fun Title(modifier: Modifier = Modifier) {
        Text(
            text = "PontoAPonto",
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.titleLarge
        )
    }

    @Composable
    fun Subtitle(modifier: Modifier = Modifier) {
        Text(
            text = "Vamos entrar na sua conta!",
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.titleMedium
        )
    }

    @Composable
    fun LoginWithGoogle(modifier: Modifier = Modifier) {
        val googleIcon = painterResource(R.drawable.google_icon)

        Button(
            onClick = { /*TODO*/ }, contentPadding = PaddingValues(10.dp),
            border = BorderStroke(width = 1.dp, Color(color = 0xFFD9D9D9)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .heightIn(min = 44.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    painter = googleIcon,
                    contentDescription = "Ícone da Google",
                    modifier = modifier.size(width = 24.dp, height = 24.dp)
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "Login com Google",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                )
                Spacer(modifier = modifier.weight(1f))
            }
        }
    }

    @Composable
    fun LoginWithMeta(modifier: Modifier = Modifier) {
        val googleIcon = painterResource(R.drawable.meta_icon)

        Button(
            onClick = { /*TODO*/ }, contentPadding = PaddingValues(10.dp),
            border = BorderStroke(width = 1.dp, Color(color = 0xFFD9D9D9)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .heightIn(min = 44.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    painter = googleIcon,
                    contentDescription = "Ícone da Meta",
                    modifier = modifier.size(width = 24.dp, height = 24.dp)
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "Login com Meta",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                )
                Spacer(modifier = modifier.weight(1f))
            }
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

    @Composable
    fun CreateAccountLink(modifier: Modifier = Modifier) {
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
                    navController?.navigate("signup")
                }
            })
    }

    @Composable
    fun AppNameWithImage(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterVertically
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoImage()
            Title()
            Subtitle()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen().Screen()
}