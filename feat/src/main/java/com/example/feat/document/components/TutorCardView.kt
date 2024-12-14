package com.example.feat.document.components

import StarRatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.TutorEntity
import com.example.feat.R
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun TutorCardView(
    modifier: Modifier = Modifier,
    tutor: TutorEntity,
    onClick: (TutorEntity) -> Unit,
    onClickFavorite: (TutorEntity) -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .clickable { onClick(tutor) }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp) // padding inside box
                .align(Alignment.BottomCenter)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    AsyncImage(
                        model = tutor.avatar ?: "",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)

                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = tutor.name ?: "",
                            style = DesignSystem.TITLE_SMALL_STYLE,
                        )
                        Text(text = tutor.country ?: "", style = DesignSystem.SUBTITLE_SMALL_STYLE)
                        // Replace with your custom RatingWidget
                        StarRatingBar(
                            maxStars = 5,
                            rating = tutor.rating?.toFloat() ?: 0f,
                            onRatingChanged = { }
                        )
                    }
                }
                if (tutor.isFavorite == true)
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(24.dp)
                            .clickable {
                            onClickFavorite(tutor)
                        },
                        tint = Color.Red
                    )
                else
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(24.dp)
                            .clickable {
                                onClickFavorite(tutor)
                            },
                        tint = Color.Black.copy(alpha = 0.7f)
                    )
            }
            if (tutor.specialties != null) {
                FlowRow {
                    tutor.specialties?.split(",")?.forEach { item ->
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(
                                    color = colorResource(id = R.color.primaryColor).copy(
                                        alpha = 0.3f
                                    )
                                )
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = item,
                                maxLines = 5,
                                style = DesignSystem.SUBTITLE_MEDIUM_STYLE,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = tutor.bio ?: "",
                maxLines = 4,
                style = DesignSystem.SUBTITLE_MEDIUM_STYLE,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Start)

            )
        }
    }
}