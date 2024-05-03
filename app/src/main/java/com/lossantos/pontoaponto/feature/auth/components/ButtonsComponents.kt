package com.lossantos.pontoaponto.feature.auth.components;

import android.widget.Button;
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

public class ButtonsComponents {
    @Composable
    fun BaseButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(
            onClick = onClick,
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFF3755C1)),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                    .heightIn(min = 44.dp)
                    .fillMaxWidth()
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            )
        }
    }

    @Composable
    fun GoBackButton(navController: NavController? = null) {
        IconButton(onClick = { navController?.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
        }
    }
}
