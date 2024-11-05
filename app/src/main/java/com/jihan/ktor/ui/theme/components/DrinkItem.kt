package com.jihan.ktor.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.ktor.data.model.Drink


@Composable
fun DrinkItem(item: Drink) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item.strDrinkThumb?.let {
            NetworkImage(
                modifier = Modifier.size(100.dp),
                url = it,
                contentScale = ContentScale.Crop
            )
        }

        item.strDrink?.let { Text(text = it, fontSize = 22.sp) }
    }
}