package com.example.feat.tutorDetail.controller

import com.example.domain.entity.TutorEntity

data class TutorDetailUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val tutorDetail: TutorEntity? = null,
)