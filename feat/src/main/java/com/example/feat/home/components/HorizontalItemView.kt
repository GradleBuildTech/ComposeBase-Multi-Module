package com.example.feat.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.components.HeadingTitle
import com.example.core.components.HorizontalList

@Composable
fun<T> HorizontalItemView(
    listData: List<T>,
    isLoading: Boolean,
    headingTitle: String,
    showMoreItem: () -> Unit,
    limitItem: Int,
    item: @Composable (T) -> Unit,
    itemSkeleton: @Composable () -> Unit,
    //........Customize.........
) {
    HeadingTitle(title = headingTitle) {
        showMoreItem()
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()){
        if (isLoading) {
            HorizontalList(size = limitItem) {
                itemSkeleton()
            }
        } else {
            HorizontalList(size = listData.size) { index ->
                item(listData[index])
            }
        }
    }
}