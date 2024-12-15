package com.example.domain.entity

data class TutorFavorites(
    var count: Int,
    var tutors : List<TutorEntity>,
    var favoriteTutors : List<TutorFavoriteEntity>
)