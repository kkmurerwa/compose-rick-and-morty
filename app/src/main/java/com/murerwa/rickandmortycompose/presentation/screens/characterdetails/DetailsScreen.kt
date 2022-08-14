package com.murerwa.rickandmortycompose.presentation.screens.characterdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.murerwa.rickandmortycompose.R
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.presentation.common.CustomTopAppBar
import com.murerwa.rickandmortycompose.presentation.common.ErrorScreen

@Composable
fun DetailScreen(
    navController: NavHostController,
    passedCharacter: CharacterItem?
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
            // Make sure we have a character to display
            if (passedCharacter == null) {
                ErrorScreen(
                    message = "No character found"
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(passedCharacter.image)
                            .crossfade(true)
                            .size(Size.ORIGINAL)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_placeholder),
                        contentDescription = stringResource(R.string.character_image_cd),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    Text(
                        text = passedCharacter.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    DetailScreen(navController = rememberNavController(), null)
}