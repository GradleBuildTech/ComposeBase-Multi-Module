package com.example.feat.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.EBookEntity

@Composable
fun ItemEBook(
    eBook: EBookEntity,
    onClick: (EBookEntity) -> Unit
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val itemEBookHeight = Constants.EBOOK_ITEM_HEIGHT

    Box(
        modifier = Modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .width((screenWidth * 0.75).dp) // width of box
            .height(itemEBookHeight.dp) // height of box
            .clickable { onClick(eBook) }
    ) {
        Row {
            AsyncImage(
                model = eBook.imageUrl ?: "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .width(130.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Text(
                    text = eBook.name ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = DesignSystem.TITLE_SMALL_STYLE.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
                Text(
                    text = eBook.description ?: "",
                    maxLines = 4,
                    style = DesignSystem.SUBTITLE_SMALL_STYLE
                )
            }
        }
    }

}