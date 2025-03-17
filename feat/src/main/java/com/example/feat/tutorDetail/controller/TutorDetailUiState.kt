package com.example.feat.tutorDetail.controller

import com.example.domain.entity.tutor.detail.TutorDetail

data class TutorDetailUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val tutorDetail: TutorDetail? = null,
)