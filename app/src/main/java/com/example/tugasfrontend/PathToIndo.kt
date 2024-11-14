package com.example.tugasfrontend

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugasfrontend.navigation.NavigationItem
import com.example.tugasfrontend.navigation.Screen
import com.example.tugasfrontend.pages.HomeScreen
import com.example.tugasfrontend.pages.MyListScreen
import com.example.pathtoindo_mobileapp.pages.SearchScreen
import com.example.tugasfrontend.pages.DetailTravelScreen
import androidx.navigation.compose.NavHost

@Composable
fun PathToIndo(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(contentPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen(navController = navController)
            }
            composable(Screen.MyList.route) {
                MyListScreen(navController = navController)
            }
            composable(
                route = "${Screen.DetailTravel.route}/{travelId}",
                arguments = listOf(navArgument("travelId") { type = NavType.IntType })
            ) { backStackEntry ->
                val travelId = backStackEntry.arguments?.getInt("travelId")
                travelId?.let {
                    DetailTravelScreen(travelId = it, navController = navController)
                }
            }

        }
    }
}

@Composable
private fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = Color(0xFF008DDA)
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Search",
                icon = Icons.Default.Search,
                screen = Screen.Search
            ),
            NavigationItem(
                title = "About Me",
                icon = Icons.Default.List,
                screen = Screen.MyList
            )
        )

        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PathToIndoPreview() {
    PathToIndo(
        navController = rememberNavController()
    )
}
