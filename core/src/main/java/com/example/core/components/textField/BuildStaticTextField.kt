package com.example.core.components.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun BuildStaticTextField(
    textController : String,
    hintText : String,
    onValueChange: () -> Unit,
    isSelected : Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                1.dp,
                if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.outline.copy(0.12F))
            .clickable {
                onValueChange()
            }
            .padding(12.dp)
    ) {
        Text(
            text = textController.ifEmpty { hintText },
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.W400,
                color = if(textController.isEmpty()) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onBackground
            )
        )
    }
}
