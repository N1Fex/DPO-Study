package ru.fefu.dpo_study.activities

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.fefu.dpo_study.R
import ru.fefu.dpo_study.views.MyActivitiesScreen
import ru.fefu.dpo_study.views.UsersActivitiesScreen
import ru.fefu.dpo_study.ui.theme.DPOStudyTheme
import ru.fefu.dpo_study.ui.theme.LightPrimaryColor
import ru.fefu.dpo_study.ui.theme.LightSecondaryColor
import ru.fefu.dpo_study.views.DetailsScreen

class ActivityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route.toString()

            val selectedTabIndex = remember { mutableIntStateOf(0) }

            DPOStudyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when(currentRoute){
                            "details" -> DetailsTopBar(navController = navController)
                            else -> ActivitiesTopTabBar(navController = navController, selectedTabIndex = selectedTabIndex)
                        }
                    },
                    bottomBar = {
                        MainBottomBar(navController = navController)
                    },
                    floatingActionButton = {
                        when(currentRoute){
                            "details" -> {}
                            else -> StartActivityFloatingButton(navController = navController)
                        }

                    }
                    ) { innerPadding ->

                    NavHost(navController=navController, startDestination = "my"){
                        composable("my"){
                            MyActivitiesScreen(modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                        composable("users"){
                            UsersActivitiesScreen(modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                        composable("details"){
                            DetailsScreen(Modifier.padding(innerPadding))
                        }
                    }

                }
            }
        }
    }
}

data class TabItem(
    val title: String,
    val icon: @Composable (() -> Unit)?,
    val route: String = ""
)

@Composable
fun StartActivityFloatingButton(
    modifier: Modifier = Modifier,
    navController: NavController
){
    FloatingActionButton(
        onClick = {},
        modifier = modifier.size(64.dp),
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "Начать активность")
    }
}

@Composable
fun CustomTabRow(
    items: List<TabItem>,
    selectedTabIndex: MutableIntState,
    navController: NavController,
    modifier: Modifier = Modifier
){
    TabRow(selectedTabIndex = selectedTabIndex.intValue, modifier = modifier){
        items.forEachIndexed{ index, item ->
            Tab(
                selected = index == selectedTabIndex.intValue,
                onClick = {
                    selectedTabIndex.intValue = index
                    if (item.route.isNotEmpty()) navController.navigate(item.route){launchSingleTop = true}
                },
                text = {
                    Text(text = item.title)
                },
                icon = item.icon,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesTopTabBar(
    navController: NavController,
    selectedTabIndex: MutableIntState,
    modifier: Modifier = Modifier
){
    val items = listOf(
        TabItem(
            title = "Моя",
            icon = null,
            route = "my"
        ),
        TabItem(
            title = "Пользователей",
            icon = null,
            route = "users"
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.toString()
    items.forEachIndexed(){ index, item ->
        if (item.route == currentRoute) selectedTabIndex.intValue = index
    }

    TopAppBar(title = {}, navigationIcon = {
        CustomTabRow(
            items = items,
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            navController = navController
            )
        }
    )

}

@Composable
fun MainBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val tabItems = listOf(
        TabItem(
            title = "Активность",
            icon = {
                Icon(Icons.Filled.Sports,
                    contentDescription = "Активность")
            }
        ),
        TabItem(
            title = "Профиль",
            icon = {
                Icon(Icons.Filled.Person,
                    contentDescription = "Профиль")
            }
        )
    )
    val selectedTabIndex = remember { mutableIntStateOf(0) }
    BottomAppBar {
        CustomTabRow(items = tabItems, selectedTabIndex = selectedTabIndex, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    navController: NavController,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Row() {
                Text(text = "Велосипед")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Filled.PedalBike, contentDescription = "Велосипед", modifier = Modifier.align(Alignment.CenterVertically))
            }
        },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "Назад"
                )
            }
        },
        actions = {
            IconButton(onClick = {}){
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Удалить")
            }
            IconButton(onClick = {}){
                Icon(imageVector = Icons.Filled.Share, contentDescription = "Поделиться")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
    )
}


