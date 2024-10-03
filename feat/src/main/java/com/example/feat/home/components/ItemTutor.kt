package com.example.feat.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.lib.constants.DesignSystem
import com.example.feat.R
import com.example.feat.home.entity.TutorEntity
import java.util.Locale

@Composable
fun ItemTutor(
    tutor: TutorEntity,
    onClick: (TutorEntity) -> Unit
){
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .width((screenWidth * 0.55).dp) // width of box
            .clickable { onClick(tutor) }
    ) {
        Column(
            modifier = Modifier
                .shadow(elevation = 5.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(10.dp) // padding inside box
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 5.dp)
            ) {
                AsyncImage(
                    model = tutor.avatar ?: "",
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = tutor.name ?: "",
                        style = DesignSystem.TITLE_SMALL_STYLE,
                    )
                    // Replace with your custom RatingWidget
                    Text(text = "${tutor.rating ?: 0.0}",)
                }
            }
            Text(
                text = tutor.bio ?: "",
                maxLines = 6,
                style = DesignSystem.SUBTITLE_SMALL_STYLE,
            )
            HorizontalDivider(
                thickness = 0.3.dp,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.primaryColor))
                        .padding(5.dp)
                        .weight(5f)
                ){
                    Text(
                        text = tutor.level?.lowercase(Locale.ROOT) ?: "Unknown",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = tutor.country ?: "",
                    style = DesignSystem.SUBTITLE_SMALL_STYLE,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}