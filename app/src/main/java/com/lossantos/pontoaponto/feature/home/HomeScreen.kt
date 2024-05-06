package com.lossantos.pontoaponto.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.Util
import com.lossantos.pontoaponto.feature.home.components.HomeComponents

class HomeScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        Scaffold(  bottomBar = {
            HomeComponents().BottomNavigation();
        }) { it ->
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
                Util().Header(title = "Boa tarde, Usuário", subtitle = "São Paulo, SP")
                MainServicesSection()
                WeatherSection()
                FollowSection()

            }

        }
    }

    @Composable
    fun MainServicesSection() {
        val annotatedText = buildAnnotatedString {
            withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyle()) {
                append("ver todos")
            }

            pushStringAnnotation(tag = "Clickable", annotation = "ver todos")
            pop()
        }

        HomeComponents().Section(
            title = "Serviços principais",
            content = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeComponents().ServiceButton(
                        title = "Transporte público",
                        icon = Icons.Filled.DirectionsTransit,
                        onClick = {}
                    )
                    HomeComponents().ServiceButton(
                        title = "Corridas",
                        icon = Icons.Filled.DirectionsCar,
                        onClick = {}
                    )
                }
            },
            titleOption = {
                ClickableText(
                    text = annotatedText,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "Clickable",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            // todo: implementar navegação para página `ver todos`
                        }
                    },
                )
            }
        )
    }

    @Composable
    fun WeatherSection(modifier: Modifier = Modifier) {
        HomeComponents().Section(
            title = "Clima e tempo",
            content = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "24°C",
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 26.sp)
                        )
                        Column {
                            Text(
                                text = "Quinta-feira, 2 de Maio",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "São Paulo, Brazil",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Icon(
                        Icons.Filled.WbSunny,
                        "Ícone de dia ensolarado",
                        modifier = modifier.size(36.dp),
                    )
                }
            },
            titleOption = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.MoreVert,
                        "Botão de gerenciar Clima e tempo",
                        modifier = modifier.size(24.dp)
                    )
                }
            }
        )
    }

    @Composable
    fun FollowSection(modifier: Modifier = Modifier) {

        HomeComponents().Section(title = "Acompanhe", content = {
            HomeComponents().MountainMap(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth())
        }, titleOption = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.MoreVert,
                    "Botão de gerenciar Acompanhe",
                    modifier = modifier.size(24.dp)
                )
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen().Screen()
}