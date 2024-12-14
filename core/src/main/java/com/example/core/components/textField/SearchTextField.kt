package com.example.core.components.textField

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    hintText: String = "Search here...",
    searchController: String,
    onSearchChange: (String) -> Unit
) {
    BuildOutlineTextField(
        textController = searchController,
        onValueChange = onSearchChange,
        modifier = modifier,
        hintText = hintText,
    )
}