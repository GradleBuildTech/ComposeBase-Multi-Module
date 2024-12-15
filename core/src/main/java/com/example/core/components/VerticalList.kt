package com.example.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalList(
    modifier: Modifier = Modifier,
    verticalItemPadding : Dp = 10.dp,
    horizontalItemPadding: Dp = 10.dp,
    size: Int,
    item: @Composable (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(size + 1) { index ->
            if (index == size)
                Spacer(modifier = modifier) else
                Box(
                    modifier = modifier.padding(
                        horizontalItemPadding,
                        if (index == 0) verticalItemPadding else 0.dp,
                        horizontalItemPadding,
                        verticalItemPadding,
                    )
                ) {
                    item(index)
                }
        }
    }
}