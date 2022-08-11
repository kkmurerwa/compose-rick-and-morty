package com.murerwa.rickandmortycompose.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.murerwa.rickandmortycompose.presentation.common.CustomTopAppBar

@Composable
fun DetailScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTopAppBar(
            title = "Details",
            onBackClick = {
                navController.popBackStack()
            }
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Details")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    DetailScreen(navController = rememberNavController())
}