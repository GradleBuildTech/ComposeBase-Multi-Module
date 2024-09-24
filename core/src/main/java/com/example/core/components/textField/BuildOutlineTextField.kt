package com.example.core.components.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.core.lib.constants.DesignSystem

@Composable
fun BuildOutlineTextField(
    modifier: Modifier? = Modifier,
    textController: String,
    hintText: String = "",
    hintStyle: TextStyle? = null,
    textStyle: TextStyle? = null,
    maxLines: Int = 1,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    borderRound: Dp? = null
) {
    OutlinedTextField(
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .padding(DesignSystem.OUTSIDE_PADDING),
        placeholder = {
            Text(
                text = hintText, style = hintStyle ?: DesignSystem.TITLE_SMALL_STYLE.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    fontWeight = FontWeight.W400
                )
            )
        },
        textStyle = textStyle ?: DesignSystem.TITLE_SMALL_STYLE.copy(
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onBackground
        ),
        value = textController,
        onValueChange = onValueChange,
        maxLines = maxLines,
        shape = RoundedCornerShape(borderRound ?: DesignSystem.BUTTON_CORNER_RADIUS),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(
                0.4F
            )
        ),
        leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null) } },
        trailingIcon = trailingIcon?.let { { Icon(it, contentDescription = null) } }
    )
}