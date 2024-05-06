package com.lossantos.pontoaponto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lossantos.pontoaponto.feature.auth.login.LoginScreen
import com.lossantos.pontoaponto.feature.auth.login.LoginWithEmailAndPasswordScreen
import com.lossantos.pontoaponto.feature.auth.forgotpassword.ForgotPasswordScreen
import com.lossantos.pontoaponto.feature.auth.signup.SignupConfirmCodeScreen
import com.lossantos.pontoaponto.feature.auth.signup.SignupPersonalDataScreen
import com.lossantos.pontoaponto.feature.auth.signup.SignupScreen
import com.lossantos.pontoaponto.feature.home.HomeScreen
import com.lossantos.pontoaponto.feature.home.components.HomeComponents
import com.lossantos.pontoaponto.ui.theme.PontoAPontoTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PontoAPontoTheme {
                // A surface container using the 'background' color from the theme
                MyApp()


            }
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("login") { LoginScreen(navController = navController).Screen() }
            composable("login_with_email_and_password") {
                LoginWithEmailAndPasswordScreen(navController = navController).Screen()
            }
            composable("forgot_password") { ForgotPasswordScreen(navController = navController).Screen() }
            composable("signup") { SignupScreen(navController = navController).Screen() }
            composable("signup_personal_data") { SignupPersonalDataScreen(navController = navController).Screen() }
            composable("signup_confirm_code") { SignupConfirmCodeScreen(navController = navController).Screen() }
            composable("home") { HomeScreen(navController = navController).Screen() }
        }
    }
}
