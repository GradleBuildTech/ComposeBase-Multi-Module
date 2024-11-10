package com.example.core.components.bottom_navigation_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
fun RowScope.BottomNavigationBarLabel(
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
            ), label = Constants.NAVIGATION_BAR_LABEL_ANIMATION
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
                .width(Constants.BACKGROUND_ICON_TAB_SIZE.dp)
                .height(Constants.BACKGROUND_ICON_TAB_SIZE.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(Constants.BACKGROUND_ICON_TAB_SIZE.dp)
                        .clip(CircleShape)
                        .background(bottomNavigationBarStyle.selectedColor),
                    contentAlignment = Alignment.Center
                ) {
                    NavIcon(
                        painter = remember { path },
                        label = tab.label,
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    NavIcon(
                        painter = remember { path },
                        label = tab.label,
                        colorTint = bottomNavigationBarStyle.unselectedColor
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = tab.label,
                        style = DesignSystem.SUBTITLE_SMALL_STYLE.copy(
                            color = bottomNavigationBarStyle.unselectedColor,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }
    }
}