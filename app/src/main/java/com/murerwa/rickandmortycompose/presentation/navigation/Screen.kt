package com.murerwa.rickandmortycompose.presentation.navigation

import android.net.Uri
import com.google.gson.Gson
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.presentation.navigation.ArgumentKeys.CHARACTER_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Detail: Screen(route = "detail_screen/{$CHARACTER_ARGUMENT_KEY}") {
        fun passCharacter(character: CharacterItem): String {
            val json = Uri.encode(Gson().toJson(character))

            return this.route.replace("{$CHARACTER_ARGUMENT_KEY}", json)
        }
    }
}