package com.example.core.components.bottom_navigation_bar

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Easing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.R

@Composable
fun BottomNavigationBar(
    paddingValues: PaddingValues = PaddingValues(10.dp),
    backgroundColor: Color = Color.White,
    elevation: Dp = 5.dp,
    roundedCornerSize: Dp = 0.dp,
    type: NavigationBarType = NavigationBarType.NONE,
    tabs: List<NavigationBarItemModel> = emptyList(),
    onItemClicked: (Int) -> Unit = {},
    currentIndex: Int = 0,
    selectedColor: Color = colorResource(id = R.color.primaryColor),
    unselectedColor: Color = colorResource(id = R.color.black),
    backgroundSelectedColor: Color = colorResource(id = R.color.primaryColor).copy(alpha = 0.3f),
    backgroundUnSelectedColor: Color = Color.Transparent,
    durationMillis: Int = 300,
    easing: Easing = EaseInOut,
    targetValueSelected: Float = 1f,
    targetValueUnselected: Float = 0.8f,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(roundedCornerSize),
            )
            .background(backgroundColor)
            .padding(
                paddingValues,
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        when (type) {
            NavigationBarType.ROW -> BottomNavigationBarRow(
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                backgroundSelectedColor = backgroundSelectedColor,
                backgroundUnSelectedColor = backgroundUnSelectedColor,
                tabs = tabs,
                modifier = Modifier,
                currentIndex = currentIndex,
                durationMillis = durationMillis,
                easing = easing,
                targetValueSelected = targetValueSelected,
                targetValueUnselected = targetValueUnselected,
                onItemClicked = onItemClicked
            )

            NavigationBarType.INDICATOR -> BottomNavigationBarIndicator(
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                backgroundSelectedColor = backgroundSelectedColor,
                backgroundUnSelectedColor = backgroundUnSelectedColor,
                tabs = tabs,
                modifier = Modifier,
                currentIndex = currentIndex,
                durationMillis = durationMillis,
                easing = easing,
                targetValueSelected = targetValueSelected,
                targetValueUnselected = targetValueUnselected,
                onItemClicked = onItemClicked
            )

            NavigationBarType.DOT -> BottomNavigationBarDot(
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                tabs = tabs,
                modifier = Modifier,
                currentIndex = currentIndex,
                durationMillis = durationMillis,
                easing = easing,
                targetValueSelected = targetValueSelected,
                targetValueUnselected = targetValueUnselected,
                onItemClicked = onItemClicked
            )

            NavigationBarType.LABEL -> BottomNavigationBarLabel(
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                tabs = tabs,
                modifier = Modifier,
                currentIndex = currentIndex,
                durationMillis = durationMillis,
                easing = easing,
                targetValueSelected = targetValueSelected,
                targetValueUnselected = targetValueUnselected,
                onItemClicked = onItemClicked
            )

            else -> {
                BottomNavigationBarNone(
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor,
                    tabs = tabs,
                    modifier = Modifier,
                    currentIndex = currentIndex,
                    durationMillis = durationMillis,
                    easing = easing,
                    targetValueSelected = targetValueSelected,
                    targetValueUnselected = targetValueUnselected,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}
