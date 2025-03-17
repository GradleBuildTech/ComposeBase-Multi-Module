package com.example.feat.tutorDetail

import StarRatingBar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.tutor.detail.TutorDetail
import com.example.feat.tutorDetail.components.TutorDetailSkeleton
import com.example.feat.tutorDetail.controller.TutorDetailUiEvent
import com.example.feat.tutorDetail.controller.TutorDetailViewModel


@Composable
fun TutorDetailScreen(
    tutorId: String,
    tutorDetailViewModel: TutorDetailViewModel = hiltViewModel()
) {
    val uiState by tutorDetailViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(tutorId) {
        tutorDetailViewModel.onEvent(TutorDetailUiEvent.FetchTutorDetail(tutorId))
    }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNullOrEmpty().not()) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }

    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.5f)),
        topBar = {
            BuildTopAppBar(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp),
                onBack = { tutorDetailViewModel.onEvent(TutorDetailUiEvent.OnBackPreviousScreen) }
            )
        }
    ) { innerPadding ->
        BuildBody(
            tutorDetail = uiState.tutorDetail,
            modifier = Modifier.padding(innerPadding),
            isLoading = uiState.isLoading
        )

    }
}

@Composable
private fun BuildBody(
    tutorDetail: TutorDetail? = null,
    modifier: Modifier,
    isLoading: Boolean,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    if (isLoading || tutorDetail == null) {
        TutorDetailSkeleton(modifier = modifier, screenWidth = screenWidth.toDouble())
    } else {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = tutorDetail.user?.avatar ?: "",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)

                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = tutorDetail.user?.name ?: "",
                        style = DesignSystem.TITLE_MEDIUM_STYLE,
                    )
                    Text(
                        text = tutorDetail.user?.country ?: "",
                        style = DesignSystem.TITLE_SMALL_STYLE
                    )
                    // Replace with your custom RatingWidget
                    StarRatingBar(
                        maxStars = 5,
                        rating = tutorDetail.rating.toFloat(),
                        size = 8f,
                        onRatingChanged = { }
                    )
                }
            }
        }
    }
}

@Composable
private fun BuildTopAppBar(
    modifier: Modifier,
    onBack: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.layoutId("backButton")
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Button",
            )
        }
        Text(
            text = "Tutorial Detail",
            modifier = Modifier.layoutId("title"),
            style = DesignSystem.TITLE_SMALL_STYLE.copy(color = Color.Black, fontSize = 20.sp)
        )
    }
}