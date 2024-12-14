package com.example.feat.document

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.Skeleton
import com.example.core.components.VerticalList
import com.example.core.lib.constants.DesignSystem
import com.example.feat.R
import com.example.feat.document.components.TimeRichText
import com.example.feat.document.components.Timer
import com.example.feat.document.components.TutorCardView
import com.example.feat.document.controller.DocumentUiEvent
import com.example.feat.document.controller.DocumentViewModel

@Composable
fun DocumentScreen(
    documentViewModel: DocumentViewModel
) {
    val uiState by documentViewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.5f)),
        topBar = {
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(
                        painterResource(R.drawable.ic_logo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(30.dp),
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = " Tutor",
                        style = DesignSystem.TITLE_LARGE_STYLE.copy(
                            color = colorResource(R.color.primaryColor),
                            fontSize = 24.sp,
                        ),
                    )
                }
                Row {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black.copy(alpha = 0.7f)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (uiState.isLoading) {
                Skeleton(
                    width = screenWidth.toDouble(), height = 250.0, leftPadding = 10.0,
                    rightPadding = 10.0,
                )
                VerticalList(size = 7) {
                    Skeleton(
                        width = screenWidth.toDouble(),
                        height = 150.0,
                        leftPadding = 10.0,
                        rightPadding = 10.0,
                        topPadding = 10.0,
                        bottomPadding = 10.0,
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp) // padding outside box
                        .clip(RoundedCornerShape(5.dp))
                        .background(colorResource(id = R.color.primaryColor))
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Timer(
                            millisInFuture = uiState.bookingInfo?.scheduleDetailEntity?.startPeriodTimestamp ?: 0L,
                            header = "The upcoming classes will begin in"
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        TimeRichText(
                            header = "The total duration of the classes is",
                            hours = (uiState.totalTime / 60).toLong(),
                            minutes = (uiState.totalTime % 60).toLong(),
                        )
                    }
                }

                if (uiState.tutors.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        VerticalList(
                            size = uiState.tutors.size,
                            item = { index ->
                                TutorCardView(
                                    modifier = Modifier.padding(
                                        10.dp,
                                    ),
                                    tutor = uiState.tutors[index],
                                    onClick = { tutor ->

                                    },
                                    onClickFavorite = { tutor ->
                                        documentViewModel.onEvent(
                                            DocumentUiEvent.AddFavoriteTutor(
                                                tutorId = tutor.id
                                            )
                                        )
                                    }
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

