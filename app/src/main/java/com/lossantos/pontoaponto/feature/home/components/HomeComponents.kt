package com.lossantos.pontoaponto.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lossantos.pontoaponto.feature.home.HomeScreen

class HomeComponents {
    @Composable
    fun Section(
        title: String,
        content: @Composable () -> Unit,
        modifier: Modifier = Modifier,
        titleOption: (@Composable () -> Unit)? = null
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = title, style = MaterialTheme.typography.labelMedium)
                titleOption?.invoke()
            }
            content()
        }
    }

    @Composable
    fun ServiceButton(
        title: String,
        icon: ImageVector,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = onClick,
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    icon,
                    contentDescription = "Botão de $title",
                    tint = Color.Black,
                    modifier = modifier.size(36.dp)
                )
                Text(
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceButtonPreview() {
    HomeComponents().ServiceButton(title = "Aluguel", icon = Icons.Filled.CarRental, onClick = {})
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun SectionPreview() {
    HomeComponents().Section(
        title = "Serviços principais",
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeComponents().ServiceButton(
                    title = "Aluguel",
                    icon = Icons.Filled.CarRental,
                    onClick = {})
                HomeComponents().ServiceButton(
                    title = "Entregas",
                    icon = Icons.Filled.DeliveryDining,
                    onClick = {})
                HomeComponents().ServiceButton(
                    title = "Voos",
                    icon = Icons.Filled.AirplanemodeActive,
                    onClick = {})
                HomeComponents().ServiceButton(
                    title = "Corridas",
                    icon = Icons.Filled.DirectionsCar,
                    onClick = {})
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeComponents().ServiceButton(
                    title = "Transporte público",
                    icon = Icons.Filled.DirectionsTransit,
                    onClick = {})
                HomeComponents().ServiceButton(
                    title = "Ofertas",
                    icon = Icons.Filled.Discount,
                    onClick = {})
            }
        },
        titleOption = {
            Text(text = "ver todos", style = MaterialTheme.typography.bodySmall)
        },
    )
}