package com.lossantos.pontoaponto.feature.auth.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class BarComponents {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppBar(navController: NavController?, modifier: Modifier = Modifier) {
        TopAppBar(title = { /*TODO*/ }, navigationIcon = {
            ButtonsComponents().GoBackButton(navController)
        }, modifier = modifier.padding(0.dp))
    }
}