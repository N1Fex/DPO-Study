package ru.fefu.dpo_study.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Surfing
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.fefu.dpo_study.ui.theme.LightActivityDateColor
import ru.fefu.dpo_study.ui.theme.LightPrimaryColor
import ru.fefu.dpo_study.ui.theme.LightSurface


data class ActivityInfo(
    val distance: String,
    val duration: String,
    val type: String,
    val endDate: String,
    val icon: ImageVector,
    var username: String = ""
)

data class CategoryDate(
    val date: String,
    val items: List<ActivityInfo>
)

@Composable
fun CategoryHeader(
    date: String,
    modifier: Modifier = Modifier
){
    Text(
        text=date,
        lineHeight = 35.sp,
        fontSize = 24.sp,
        color = LightActivityDateColor,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
    )
}

@Composable
fun CategoryItem(
    navController: NavController,
    info: ActivityInfo,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
            .clickable(onClick = {
                navController.navigate("details"){launchSingleTop = true}
            }), //ЗАТЫЧКА
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = LightSurface)
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column() {
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                    Column() {
                        Text(
                            text = info.distance,
                            lineHeight = 35.sp,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W700
                        )
                        Text(
                            text = info.duration,
                            fontSize = 16.sp
                        )
                    }
                    Text(
                        text = info.username,
                        modifier = Modifier.align(Alignment.TopEnd),
                        color = LightPrimaryColor
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(){
                        Text(text = info.type)
                        Icon(
                            imageVector = info.icon,
                            contentDescription = info.type,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(16.dp)
                                .align(Alignment.CenterVertically)

                        )
                    }
                    Text(
                        text = info.endDate,
                        color = LightActivityDateColor,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryLazyColumn(
    navController: NavController,
    dates: List<CategoryDate>,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier.padding(16.dp, 8.dp)){
        dates.forEach { it1->
            stickyHeader {
                CategoryHeader(date = it1.date)
            }
            it1.items.forEach {
                item {
                    CategoryItem(info = it, navController = navController)
                }
            }
        }


    }
}

@Composable
fun UsersActivitiesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val activities = listOf(
        CategoryDate(
            date = "Вчера",
            listOf(
                ActivityInfo(
                    distance = "14.32 км",
                    duration =  "2 часа 26 минут",
                    type = "Серфинг",
                    endDate = "14 часов назад",
                    icon = Icons.Filled.Surfing,
                    username = "@van_darkholme"

                ),
                ActivityInfo(
                    distance = "228м",
                    duration =  "14 часов 26 минут",
                    type = "Отдых",
                    endDate = "14 часов назад",
                    icon = Icons.Filled.Bed,
                    username = "@tetrtret"
                ),
                ActivityInfo(
                    distance = "10 км",
                    duration =  "2 часа 26 минут",
                    type = "Езда на кадилак",
                    endDate = "14 часов назад",
                    icon = Icons.Filled.DirectionsCar,
                    username = "@n1fex"
                ),
            )
        )
    )
    CategoryLazyColumn(dates = activities, modifier = modifier, navController = navController)
}

@Composable
fun MyActivitiesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val activities = listOf(
        CategoryDate(
            date = "Вчера",
            listOf(
                ActivityInfo(
                    distance = "14.32 км",
                    duration =  "2 часа 26 минут",
                    type = "Серфинг",
                    endDate = "14 часов назад",
                    icon = Icons.Filled.Surfing
                )
            )
        ),
        CategoryDate(
            date = "Май 2022 года",
            listOf(
                ActivityInfo(
                    distance = "1000 м",
                    duration =  "60 минут",
                    type = "Велосипед",
                    endDate = "29.05.2022",
                    icon = Icons.Filled.PedalBike
                )
            )
        )
    )
    CategoryLazyColumn(dates = activities, modifier = modifier, navController = navController)
}

@Composable
fun DetailsScreen(
    //navController: NavController,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.padding(32.dp, 0.dp)){
        Box(modifier = Modifier.padding(0.dp, 12.dp)){
            Column(){
                Text(
                    text = "14.32 км",
                    lineHeight = 35.sp,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = "14 часов назад",
                    color = LightActivityDateColor
                )
            }
        }
        Box(modifier = Modifier.padding(0.dp, 12.dp)){
            Column(){
                Text(
                    text = "1ч 42 мин",
                    lineHeight = 35.sp,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = buildAnnotatedString {
                        append("Старт   ")
                        withStyle(style = SpanStyle(color = LightActivityDateColor)){
                            append("14:49")
                        }
                        append("   |   Финиш   ")
                        withStyle(style = SpanStyle(color = LightActivityDateColor)){
                            append("16:31")
                        }
                    }
                )
            }
        }
        val text = remember { mutableStateOf("") }
        OutlinedTextField(
            value = text.value,
            onValueChange = {text.value = it},
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            placeholder = { Text("Комментарий") }
        )
    }
}