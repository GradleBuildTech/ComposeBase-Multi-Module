package com.example.domain.entity

data class TutorFavorites(
    var tutors : List<TutorEntity>,
    var favoriteTutors : List<TutorFavoriteEntity>
)