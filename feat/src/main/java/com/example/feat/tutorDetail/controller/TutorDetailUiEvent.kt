package com.example.feat.tutorDetail.controller

sealed class TutorDetailUiEvent {
    data class FetchTutorDetail(val tutorId: String) : TutorDetailUiEvent()
    data object OnBackPreviousScreen : TutorDetailUiEvent()
}