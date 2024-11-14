package com.example.tugasfrontend.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object MyList : Screen("my_list")
    object DetailTravel : Screen("detail")
}

