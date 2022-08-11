package com.murerwa.rickandmortycompose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.murerwa.rickandmortycompose.presentation.navigation.SetUpNavGraph
import com.murerwa.rickandmortycompose.presentation.theme.RickAndMortyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                val navController = rememberNavController()

                SetUpNavGraph(navController = navController)
            }
        }
    }
}