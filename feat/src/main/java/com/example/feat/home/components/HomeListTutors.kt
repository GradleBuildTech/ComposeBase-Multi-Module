package com.example.feat.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.HeadingTitle
import com.example.core.components.HorizontalList
import com.example.core.lib.constants.Constants
import com.example.domain.entity.tutor.TutorEntity
import com.example.feat.home.controller.HomeViewModel

@Composable
fun HomeListTutors(
    onClickListTutors: () -> Unit,
    onClickItemTutor: (TutorEntity) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsState()
    val tutors = uiState.tutors
    val isLoadingTutors = uiState.isLoadingTutors

    HeadingTitle(title = "\uD83D\uDC68\u200D\uD83C\uDFEB Top tutors") {
        onClickListTutors()
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        if (isLoadingTutors) {
            HorizontalList(size = Constants.TUTOR_LIMIT_ITEM) {
                ItemTutorSkeleton()
            }
        } else {
            HorizontalList(size = tutors.size) { index ->
                ItemTutor(tutors[index]) {
                    onClickItemTutor(it)
                }
            }
        }
    }
}