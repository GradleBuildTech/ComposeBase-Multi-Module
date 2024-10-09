package com.example.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.R
import com.example.core.lib.constants.DesignSystem

@Composable
fun HeadingTitle(
    title: String,
    onClickSeeMore: () -> Unit,
){
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = DesignSystem.TITLE_MEDIUM_STYLE,
        )
        Text(
            text = "See more",
            style = DesignSystem.SUBTITLE_SMALL_STYLE.copy(
                color = colorResource(R.color.primaryColor),
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.clickable {
                onClickSeeMore()
            }
        )
    }
}