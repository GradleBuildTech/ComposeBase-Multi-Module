package com.example.feat.document.controller

sealed class DocumentUiEvent {
    data class AddFavoriteTutor(val tutorId: String) : DocumentUiEvent()
    data class OpenTutorDetail(val tutorId: String) : DocumentUiEvent()
    data object FetchTutors : DocumentUiEvent()
}