package com.example.feat.search.components

import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.components.BuildButton
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.ContentCategoryEntity
import com.example.feat.R
import com.google.accompanist.flowlayout.FlowRow
import java.util.Locale


@Composable
fun ContentCategoriesBottomSheet(
    contentCategories: List<ContentCategoryEntity>,
    contentCategorySelected: ContentCategoryEntity? = null,
    onCategorySelected: (ContentCategoryEntity) -> Unit,
    onApplyFilter: () -> Unit,
) {
    Box(modifier = Modifier.padding(16.dp)) {
        Column {
            FlowRow(
                mainAxisSpacing = 6.dp,
                crossAxisSpacing = (-8).dp
            ) {
                contentCategories.forEach { category ->
                    ContentCategoryChip(
                        category = category,
                        selected = category == contentCategorySelected,
                        onCategorySelected = onCategorySelected,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            BuildButton(
                color = colorResource(id = R.color.primaryColor),
                onPress = onApplyFilter,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "Apply",
                    style = DesignSystem.TITLE_SMALL_STYLE.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
fun ContentCategoryChip(
    category: ContentCategoryEntity,
    selected: Boolean = false,
    onCategorySelected: (ContentCategoryEntity) -> Unit
) {
    val selectedColor = colorResource(id = R.color.primaryColor)
    val unselectedColor = colorResource(id = R.color.black)
    val hintColor = colorResource(id = R.color.hintColor)

    AssistChip(
        onClick = {
            onCategorySelected(category)
        },
        label = {
            Text(
                category.title.uppercase(Locale.ROOT),
                style = DesignSystem.SUBTITLE_SMALL_STYLE.copy(
                    color = if (selected) remember {
                        selectedColor
                    } else remember {
                        unselectedColor
                    }
                )
            )
        },
        leadingIcon = {
            if (selected) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Check",
                    tint = remember {
                        selectedColor
                    },
                )
            }
        },
        colors = AssistChipDefaults.assistChipColors().copy(
            containerColor = if (selected) remember {
                selectedColor.copy(alpha = 0.1f)
            } else remember {
                hintColor.copy(alpha = 0.3f)
            },
        ),
        border = null
    )
}