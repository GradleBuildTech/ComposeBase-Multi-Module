package com.example.feat.document

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.components.Skeleton
import com.example.core.components.VerticalList
import com.example.core.components.pagination.DefaultPagination
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.TutorEntity
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
        topBar = { BuildDocumentTopAppBar() }
    ) { innerPadding ->
        BuildBody(
            modifier = Modifier.padding(innerPadding),
            tutors = uiState.tutors,
            isLoading = uiState.isLoading,
            screenWidth = screenWidth,
            onFetchTutors = { documentViewModel.onEvent(DocumentUiEvent.FetchTutors) },
            onAddFavoriteTutor = { tutorId ->
                documentViewModel.onEvent(DocumentUiEvent.AddFavoriteTutor(tutorId))
            },
            startPeriodTimestamp = uiState.bookingInfo?.scheduleDetailEntity?.endPeriodTimestamp ?: 0L,
            totalTime = uiState.totalTime,
        )
    }
}

@Composable
private fun BuildBody(
    modifier: Modifier,
    tutors: List<TutorEntity>,
    isLoading: Boolean,
    screenWidth: Int,
    startPeriodTimestamp: Long = 0L,
    totalTime: Int = 0,
    onFetchTutors: () -> Unit,
    onAddFavoriteTutor: (String) -> Unit,
) {
    Column(modifier = modifier) {
        if (isLoading && tutors.isEmpty()) {
            Skeleton(
                width = screenWidth.toDouble(), height = 250.0, leftPadding = 10.0,
                rightPadding = 10.0,
            )
            VerticalList(size = 7) { TutorSkeleton(screenWidth) }
        } else {
            BuildTimeLine(
                startPeriodTimestamp = startPeriodTimestamp,
                totalTime = totalTime,
            )

            if (tutors.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DefaultPagination(
                        bodyPadding = PaddingValues(vertical = 10.dp),
                        itemBuilder = { item ->
                            TutorCardView(
                                modifier = Modifier.padding(horizontal = 10.dp),
                                tutor = item,
                                onClick = { _ -> },
                                onClickFavorite = { tutor ->
                                    onAddFavoriteTutor(tutor.id)
                                }
                            )
                        },
                        isLoading = isLoading,
                        skeletonComponent = { TutorSkeleton(screenWidth) },
                        items = tutors,
                        listenScrollBottom = {
                            onFetchTutors()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BuildTimeLine(
    startPeriodTimestamp: Long,
    totalTime: Int,
) {
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
                millisInFuture = startPeriodTimestamp,
                header = "The upcoming classes will begin in"
            )
            Spacer(modifier = Modifier.size(10.dp))
            TimeRichText(
                header = "The total duration of the classes is",
                hours = (totalTime / 60).toLong(),
                minutes = (totalTime % 60).toLong(),
            )
        }
    }
}

@Composable
private fun BuildDocumentTopAppBar() {
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


@Composable
private fun TutorSkeleton(screenWidth: Int) {
    Skeleton(
        width = screenWidth.toDouble(),
        height = 150.0,
        leftPadding = 10.0,
        rightPadding = 10.0,
        topPadding = 10.0,
        bottomPadding = 10.0,
    )
}
