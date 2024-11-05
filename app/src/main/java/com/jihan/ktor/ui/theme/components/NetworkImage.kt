package com.jihan.ktor.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: Any?,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.None,
) {


    val painter = rememberAsyncImagePainter(
        url
    )

    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}