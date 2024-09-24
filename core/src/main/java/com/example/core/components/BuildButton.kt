package com.example.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/// A button that can be used to build a button with a custom design.
/// This button can be used to build a button with a custom design.
/// The button can be customized with the following parameters:

///Example usage:
///```kotlin
///BuildButton(
///    color = MaterialTheme.colorScheme.primary,
///    radius = 10f,
///    width = 200.dp,
///    height = 50.dp,
///    enableWidth = true,
///    loading = false,
///    borderColor = MaterialTheme.colorScheme.primary,
///    onPress = { /* Handle button press */ },
///) {
///    Text(
///        text = "Button",
///        style = MaterialTheme.typography.button
///    )

///}
///```

@Composable
fun BuildButton(
    /// The color of the button.
    color: Color = MaterialTheme.colorScheme.primary,
    /// The radius of the button.
    radius: Float = 10f,
    /// The width of the button.
    width: Dp? = null,
    /// The height of the button.
    height: Dp? = null,
    /// A flag to enable the width of the button.
    enableWidth: Boolean = true,
    /// A flag to enable the loading state of the button.
    loading: Boolean = false,
    /// The border color of the button.
    borderColor: Color? = null,
    /// The action to be performed when the button is pressed.
    onPress: () -> Unit,
    /// The content of the button.
    content: @Composable () -> Unit
) {
    /// The state of the button when it is hovered.
    val isHovered by remember { mutableStateOf(false) }
    /// The background color of the button.
    /// animateColorAsState is used to animate the color change when the button is hovered.
    val backgroundColor by animateColorAsState(
        if (isHovered) color.copy(alpha = 0.75f) else color, label = ""
    )
    val borderColorState = borderColor ?: color

    Box(
        modifier = Modifier
            .then(
                if (enableWidth && width != null) Modifier.width(width) else Modifier
            )
            .then(
                if (height != null) Modifier.height(height) else Modifier
            )
            .clip(RoundedCornerShape(radius.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = if (isHovered) Color.Transparent else borderColorState,
                shape = RoundedCornerShape(radius.dp)
            )
            .shadow(if (isHovered) 6.dp else 0.dp)
    ) {
        Button(
            onClick = { if (!loading) onPress() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(radius.dp),
            enabled = !loading
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size((height ?: 50.dp) * 0.6f),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                content()
            }
        }
    }
}
