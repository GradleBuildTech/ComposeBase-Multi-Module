package com.example.domain.entity.tutor

data class TutorFavorites(
    var count: Int,
    var tutors : List<TutorEntity>,
    var favoriteTutors : List<TutorFavoriteEntity>
)