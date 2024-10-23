package com.example.core.components.bottom_navigation_bar

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Easing
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.core.lib.constants.Constants

@Composable
fun RowScope.BottomNavigationBarIndicator(
    selectedColor: Color,
    unselectedColor: Color,
    modifier: Modifier = Modifier,
    tabs: List<NavigationBarItemModel> = emptyList(),
    currentIndex: Int = 0,
    durationMillis: Int = 300,
    easing: Easing = EaseInOut,
    targetValueSelected: Float = 1f,
    targetValueUnselected: Float = 0.8f,
    onItemClicked: (Int) -> Unit = {},
    backgroundSelectedColor: Color,
    backgroundUnSelectedColor: Color,
) {
    tabs.forEachIndexed() { index, tab ->
        val isSelected = currentIndex == index
        val path = painterResource(id = tab.idResourceIcon)
        val animatedValue by animateFloatAsState(
            targetValue = if (isSelected) targetValueSelected else targetValueUnselected,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = easing
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
                        backgroundSelectedColor
                    } else remember {
                        backgroundUnSelectedColor
                    }
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            NavIcon(
                painter = remember { path },
                label = tab.label,
                colorTint = if (isSelected) remember {
                    selectedColor
                } else remember {
                    unselectedColor
                },
            )
        }
    }

}

