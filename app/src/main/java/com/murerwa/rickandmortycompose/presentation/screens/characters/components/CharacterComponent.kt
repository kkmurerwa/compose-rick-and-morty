package com.murerwa.rickandmortycompose.presentation.screens.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.murerwa.rickandmortycompose.R
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.presentation.capitalizeString

@Composable
fun CharacterComponent(
    character: CharacterItem?,
    onClick: (CharacterItem) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(all = 10.dp)
                .clickable {
                    onClick(character!!)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character?.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = stringResource(R.string.character_image_cd),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
                    .width(100.dp)
                    .height(100.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${character?.name}",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    text = "${character?.status?.capitalizeString()}",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (character?.status) {
                        "Alive" -> Color(0xFF0BF579)
                        "Dead" -> Color.Red
                        else -> Color(0xFFF5CF14)
                    }
                )
                Text(
                    text = "${character?.species}"
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CharacterComponentPreview() {
    CharacterComponent(null) { }
}