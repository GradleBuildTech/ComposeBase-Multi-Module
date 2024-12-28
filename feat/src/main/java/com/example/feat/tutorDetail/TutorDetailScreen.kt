package com.example.feat.tutorDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feat.tutorDetail.components.TutorDetailSkeleton
import com.example.feat.tutorDetail.controller.TutorDetailUiEvent
import com.example.feat.tutorDetail.controller.TutorDetailViewModel


@Composable
fun TutorDetailScreen(
    tutorId: String,
    tutorDetailViewModel: TutorDetailViewModel = hiltViewModel()
) {
    val uiState by tutorDetailViewModel.uiState.collectAsState()

    LaunchedEffect(tutorId) {
        tutorDetailViewModel.onEvent(TutorDetailUiEvent.FetchTutorDetail(tutorId))
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.5f)),
        topBar = {
            BuildTopAppBar(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp),
                onBack = { tutorDetailViewModel.onEvent(TutorDetailUiEvent.OnBackPreviousScreen) }
            )
        }
    ) { innerPadding ->
        BuildBody(modifier = Modifier.padding(innerPadding))

    }
}

@Composable
private fun BuildBody(
    modifier: Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    TutorDetailSkeleton(modifier = modifier, screenWidth = screenWidth.toDouble())
}

@Composable
private fun BuildTopAppBar(
    modifier: Modifier,
    onBack: () -> Unit
) {
    Row(
        modifier = modifier
    ) {

    }
}