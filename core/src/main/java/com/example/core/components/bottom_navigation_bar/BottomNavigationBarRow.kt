package com.example.core.components.bottom_navigation_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.components.bottom_navigation_bar.components.NavIcon
import com.example.core.components.bottom_navigation_bar.dataClass.BottomNavigationBarStyle
import com.example.core.components.bottom_navigation_bar.dataClass.NavigationBarItemModel
import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DesignSystem

@Composable
fun BottomNavigationBarRow(
    bottomNavigationBarStyle: BottomNavigationBarStyle,
    modifier: Modifier = Modifier,
    tabs: List<NavigationBarItemModel> = emptyList(),
    currentIndex: Int = 0,
    onItemClicked: (Int) -> Unit = {},
) {
    tabs.forEachIndexed() { index, tab ->
        val isSelected = currentIndex == index
        val path = painterResource(id = tab.idResourceIcon)
        val animatedValue by animateFloatAsState(
            targetValue = if (isSelected) bottomNavigationBarStyle.targetValueSelected else bottomNavigationBarStyle.targetValueUnselected,
            animationSpec = tween(
                durationMillis = bottomNavigationBarStyle.durationMillis,
                easing = bottomNavigationBarStyle.easing
            ), label = Constants.NAVIGATION_BAR_ROW_ANIMATION
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { onItemClicked(index) })
                }
                .graphicsLayer(
                    scaleX = animatedValue, scaleY = animatedValue
                )
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = if (isSelected) remember {
                        bottomNavigationBarStyle.backgroundSelectedColor
                    } else remember {
                        bottomNavigationBarStyle.backgroundUnSelectedColor
                    }
                )
                .padding(8.dp)
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
            if (isSelected) {
                Text(
                    text = tab.label,
                    modifier = Modifier.padding(start = 8.dp),
                    style = DesignSystem.SUBTITLE_SMALL_STYLE.copy(
                        color = bottomNavigationBarStyle.selectedColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

