package com.example.core.extensions

import androidx.compose.foundation.lazy.LazyListState

/// Check if the list is scrolled to the end
fun LazyListState.isScrolledToEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
