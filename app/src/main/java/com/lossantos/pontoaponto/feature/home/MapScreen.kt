package com.lossantos.pontoaponto.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Accessible
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Beenhere
import androidx.compose.material.icons.outlined.ControlCamera
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lossantos.pontoaponto.feature.Util
import com.lossantos.pontoaponto.feature.home.components.HomeComponents

class MapScreen(private val navController: NavController? = null) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        Scaffold( ) { it ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
            )
            {
                MainServicesSection()

            }

        }
    }

    @Composable
    fun MainServicesSection() {


        Box() {
            HomeComponents().MountainMap(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(), {})
            Box(modifier = Modifier
                .padding(vertical = 50.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {

                IconButton(
                    onClick = { navController?.navigate("home") },
                    modifier = Modifier
                        .then(Modifier.size(70.dp))
                        .shadow(
                            elevation = 7.dp,
                            shape = ShapeDefaults.Large
                        )
                        .align(Alignment.TopCenter)
                        .background(color = Color.White, shape = ShapeDefaults.Large)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "content description",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = { navController?.navigate("home") },
                    modifier = Modifier
                        .then(Modifier.size(70.dp))
                        .align(Alignment.TopEnd)
                        .shadow(
                            elevation = 7.dp,
                            shape = ShapeDefaults.Large
                        )
                        .background(color = Color.White, shape = ShapeDefaults.Large)
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "content description",

                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }

                Column {
    Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 25.dp)) {
        IconButton(
            onClick = { navController?.navigate("home") },
            modifier = Modifier
                .then(Modifier.size(70.dp))
                .shadow(
                    elevation = 7.dp,
                    shape = ShapeDefaults.Large
                )
                .background(color = Color.White, shape = ShapeDefaults.Large)
        ) {
            Icon(
                Icons.Outlined.Beenhere,
                contentDescription = "content description",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        IconButton(
            onClick = { navController?.navigate("home") },
            modifier = Modifier
                .then(Modifier.size(70.dp))
                .shadow(
                    elevation = 7.dp,
                    shape = ShapeDefaults.Large
                )
                .background(color = Color.White, shape = ShapeDefaults.Large)
        ) {
            Icon(
                Icons.Outlined.ControlCamera,
                modifier = Modifier.size(40.dp),
                contentDescription = "content description",
                tint = Color.Black
            )
        }
    }
    NormalTextField(label = "Para Onde?") {
        Icon(
            Icons.Filled.Map,
            modifier = Modifier.size(60.dp).padding(10.dp),
            contentDescription = "content description",
            tint = Color.Black
        )
    }


}

            }
        }
    }

}

@Composable
fun NormalTextField(
    label: String,
    icon: @Composable (() -> Unit)
) {
    val (text, setText) = mutableStateOf("")
    TextField(
        modifier = Modifier.fillMaxWidth()
        .shadow(
            elevation = 7.dp,
            shape = ShapeDefaults.Large
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White
        ),
        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
        leadingIcon = icon,
        shape = ShapeDefaults.Large,
        value = text,
        onValueChange = setText,
        placeholder = { Text(text = label, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 25.sp) }
    )
}
@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen().Screen()
}