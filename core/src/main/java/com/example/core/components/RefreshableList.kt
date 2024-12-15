package com.example.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.core.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun RefreshableList(
    size: Int,
    onRefresh: () -> Unit,
    item: @Composable (Int) -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = colorResource(id = R.color.primaryColor)
            )
        }
    ) {
        VerticalList(size = size, item = item)
    }
}