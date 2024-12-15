package com.example.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.lib.constants.DesignSystem

@Composable
fun NotFound(
    item: (@Composable () -> Unit)?,
) {
    item ?: Text(
        text = "No courses found",
        modifier = Modifier
            .padding(20.dp),
        style = DesignSystem.TITLE_SMALL_STYLE
    )
}