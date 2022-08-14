package com.murerwa.rickandmortycompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.presentation.navigation.ArgumentKeys.CHARACTER_ARGUMENT_KEY
import com.murerwa.rickandmortycompose.presentation.navigation.paramtypes.CharacterItemParamType
import com.murerwa.rickandmortycompose.presentation.screens.characterdetails.DetailScreen
import com.murerwa.rickandmortycompose.presentation.screens.characters.HomeScreen

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
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(CHARACTER_ARGUMENT_KEY) {
                    type = CharacterItemParamType()
                }
            )
        ) {
            val passedCharacter  = it.arguments
                ?.getParcelable(CHARACTER_ARGUMENT_KEY) as CharacterItem?

            DetailScreen(navController, passedCharacter)
        }
    }
}