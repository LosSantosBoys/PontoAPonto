package com.lossantos.pontoaponto.feature.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.Util
import com.lossantos.pontoaponto.feature.history.components.TripTile
import com.lossantos.pontoaponto.feature.home.components.HomeComponents
import com.lossantos.pontoaponto.models.TravelModel
import com.lossantos.pontoaponto.models.TravelState
import com.lossantos.pontoaponto.models.TravelType
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.Locale

class HistoryScreen(private val navController: NavController? = null) {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        Scaffold(bottomBar = { HomeComponents().BottomNavigation(); }) { it ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 24.dp, vertical = 30.dp)
            ) {
                Util().Header(title = "Histórico")
                LastTravel()
                History()
            }
        }
    }

    @Composable
    fun LastTravel(modifier: Modifier = Modifier) {
        HomeComponents().Section(title = "Última viagem", content = {
            HomeComponents().MountainMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                onClick = { navController?.navigate("map_screen") }
            )
        }, titleOption = {
            IconButton(onClick = { navController?.navigate("map_screen") }) {
                Icon(
                    Icons.Filled.MoreVert,
                    "Botão de gerenciar última viagem.",
                    modifier = Modifier.size(24.dp)
                )
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun History(modifier: Modifier = Modifier) {
        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf<String>("Todas", "Planejadas", "Concluídas", "Canceladas")
        val allTrips = listOf<TravelModel>(
            TravelModel(
                address = "R. Chabad, 124",
                price = 26.19,
                state = TravelState.DONE,
                type = TravelType.CAR,
                dateTime = LocalDateTime.now()
            ),
            TravelModel(
                address = "R. Prof. Atílio Innocenti, 53",
                price = 16.04,
                state = TravelState.DONE,
                type = TravelType.CAR,
                dateTime = LocalDateTime.of(2024, Month.APRIL, 16, 13, 42),
            ),
            TravelModel(
                address = "Vila Canaã",
                price = 12.43,
                state = TravelState.DONE,
                type = TravelType.CAR,
                dateTime = LocalDateTime.of(2024, Month.APRIL, 12, 18, 42),
            ),
        )
        val colorScheme = MaterialTheme.colorScheme

        Column {
            LazyRow(
                modifier = modifier,
                contentPadding = PaddingValues(0.dp),
            ) {
                items(tabs.size) { index ->
                    val title = tabs[index]

                    SuggestionChip(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        onClick = {
                            selectedTabIndex = index
                        },
                        label = { Text(title) },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = if (selectedTabIndex == index) colorScheme.primary else colorScheme.surface,
                            labelColor = if (selectedTabIndex == index) colorScheme.onPrimary else colorScheme.onSurface,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            when (selectedTabIndex) {
                0 -> {
                    LazyColumn {
                        items(allTrips) { trip: TravelModel ->
                            var leadingIcon = Icons.Filled.DirectionsCar
                            var tripState: String? = null

                            leadingIcon = when (trip.type) {
                                TravelType.AIRPLANE -> Icons.Filled.AirplanemodeActive
                                TravelType.BOAT -> Icons.Filled.DirectionsBoat
                                TravelType.BIKE -> Icons.Filled.PedalBike
                                else -> Icons.Filled.DirectionsCar
                            }

                            tripState = when (trip.state) {
                                TravelState.DONE -> "concluída"
                                TravelState.PLANNED -> "planejada"
                                TravelState.CANCELLED -> "cancelada"
                            }

                            val dateFormatter = DateTimeFormatter.ofPattern(
                                "d 'de' MMMM - HH:mm",
                                Locale("pt", "BR")
                            )
                            val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

                            TripTile(
                                leadingIcon = leadingIcon,
                                title = trip.address,
                                subtitle = trip.dateTime.format(dateFormatter),
                                trailingTitle = currencyFormatter.format(trip.price),
                                trailingSubtitle = tripState
                            )
                        }
                    }
                }

                1 -> {
                    Text("Planejadas")
                }

                2 -> {
                    // Display completed trips
                    Text("Concluídas - Completed trips displayed here")
                }

                3 -> {
                    // Display canceled trips
                    Text("Canceladas - Canceled trips displayed here")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen().Screen()
}