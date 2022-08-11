package com.murerwa.rickandmortycompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.murerwa.rickandmortycompose.ui.screens.DetailScreen
import com.murerwa.rickandmortycompose.ui.screens.HomeScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route
        ) {
            DetailScreen(navController)
        }
    }
}