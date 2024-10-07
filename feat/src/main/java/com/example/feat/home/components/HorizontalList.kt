package com.example.feat.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.lib.constants.Constants

@Composable
fun HorizontalList(
    size: Int,
    item: @Composable (Int) -> Unit
) {
    LazyRow {
        items(size + 1) { index ->
            if (index == size)
                Spacer(modifier = Modifier) else
                Box(
                    modifier = Modifier.padding(
                        if (index == 0) 10.dp else 0.dp,
                        0.dp,
                        10.dp,
                        0.dp,
                    )
                ) {
                    item(index)
                }
        }
    }
}