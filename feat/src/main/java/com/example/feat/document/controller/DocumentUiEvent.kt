package com.example.feat.document.controller

sealed class DocumentUiEvent {
    class AddFavoriteTutor(val tutorId: String) : DocumentUiEvent()
}