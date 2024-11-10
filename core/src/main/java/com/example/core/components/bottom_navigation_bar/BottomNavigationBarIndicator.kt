package com.example.core.components.bottom_navigation_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.core.components.bottom_navigation_bar.components.NavIcon
import com.example.core.components.bottom_navigation_bar.dataClass.BottomNavigationBarStyle
import com.example.core.components.bottom_navigation_bar.dataClass.NavigationBarItemModel
import com.example.core.lib.constants.Constants

@Composable
fun RowScope.BottomNavigationBarIndicator(
    modifier: Modifier = Modifier,
    tabs: List<NavigationBarItemModel> = emptyList(),
    currentIndex: Int = 0,
    onItemClicked: (Int) -> Unit = {},
    bottomNavigationBarStyle: BottomNavigationBarStyle
) {
    tabs.forEachIndexed() { index, tab ->
        val isSelected = currentIndex == index
        val path = painterResource(id = tab.idResourceIcon)
        val animatedValue by animateFloatAsState(
            targetValue = if (isSelected) bottomNavigationBarStyle.targetValueSelected else bottomNavigationBarStyle.targetValueUnselected,
            animationSpec = tween(
                durationMillis = bottomNavigationBarStyle.durationMillis,
                easing = bottomNavigationBarStyle.easing
            ), label = Constants.NAVIGATION_BAR_INDICATOR_ANIMATION
        )

        Box(
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { onItemClicked(index) })
                }
                .graphicsLayer(
                    scaleX = animatedValue, scaleY = animatedValue
                )
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = if (isSelected) remember {
                        bottomNavigationBarStyle.backgroundSelectedColor
                    } else remember {
                        bottomNavigationBarStyle.backgroundUnSelectedColor
                    }
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            NavIcon(
                painter = remember { path },
                label = tab.label,
                colorTint = if (isSelected) remember {
                    bottomNavigationBarStyle.selectedColor
                } else remember {
                    bottomNavigationBarStyle.unselectedColor
                },
            )
        }
    }

}

