package com.example.core.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun VerticalList(
    size: Int,
    item: @Composable (Int) -> Unit
) {
    LazyColumn {
        items(size) { index ->
            item(index)
        }
    }
}