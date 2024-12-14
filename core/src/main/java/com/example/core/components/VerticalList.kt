package com.example.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalList(
    size: Int,
    item: @Composable (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(size + 1) { index ->
            if (index == size)
                Spacer(modifier = Modifier) else
                Box(
                    modifier = Modifier.padding(
                        10.dp,
                        if (index == 0) 10.dp else 0.dp,
                        10.dp,
                        10.dp,
                    )
                ) {
                    item(index)
                }
        }
    }
}