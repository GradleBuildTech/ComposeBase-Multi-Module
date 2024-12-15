package com.example.core.components.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.extensions.isScrolledToEnd
import kotlinx.coroutines.launch


/// Default Pagination Component
/// This component is used to create a pagination list with a loading indicator at the end of the list
/// How to use
/// ```kotlin
/// DefaultPagination(
///     modifier = Modifier,
///     itemBuilder = { item ->
///         // Your item view
///     },
///     items = items,
///     isLoading = isLoading,
///     listenScrollBottom = {
///         // Your function to listen to the scroll bottom
///     }
/// )
/// ```


@Composable
fun <T> DefaultPagination(
    modifier: Modifier = Modifier,
    itemBuilder: @Composable (T) -> Unit,
    items: List<T>,
    isLoading: Boolean = false,
    // Skeleton component to show loading indicator
    skeletonComponent: (@Composable () -> Unit)? = null,
    skeletonCountable: Int = 2,

    space: Dp = 8.dp,
    bodyPadding: PaddingValues = PaddingValues(0.dp),
    listenScrollBottom: () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    /// Check if the list is scrolled to the end
    val endOfListReached by remember {
        derivedStateOf {
            listState.isScrolledToEnd()
        }
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached && isLoading.not()) {
            listenScrollBottom()
        }
    }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            coroutineScope.launch {
                /// Scroll to the last item when loading
                if (items.isNotEmpty()) {
                    listState.scrollToItem(items.size + 1)
                }
            }
        }
    }

    LazyColumn(
        state = listState,
        userScrollEnabled = true,
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space),
        contentPadding = bodyPadding
    ) {
        itemsIndexed(items) { _, item ->
            itemBuilder(item)
        }

        if (isLoading) {
            item {
                if (skeletonComponent != null) {
                    repeat(skeletonCountable) {
                        skeletonComponent()
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
