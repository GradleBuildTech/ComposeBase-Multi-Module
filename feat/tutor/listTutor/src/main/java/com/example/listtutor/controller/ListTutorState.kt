package com.example.listtutor.controller

import com.example.domain.entity.TutorEntity

data class ListTutorState(
    val isLoading: Boolean = false,
    val tutors: List<TutorEntity> = emptyList()
)