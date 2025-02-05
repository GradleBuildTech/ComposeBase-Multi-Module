package com.example.domain.mapper

import com.example.data.model.response.tutor.TutorFavoriteModel
import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
import com.example.domain.entity.TutorEntity
import com.example.domain.entity.TutorFavoriteEntity
import com.example.domain.entity.TutorFavorites

fun TutorModel.toEntity() : TutorEntity {
    return TutorEntity(
        id = this.id ?: "",
        name = this.name,
        email = this.email,
        bio = this.bio,
        avatar = this.avatar,
        level = this.level,
        country = this.country,
        rating = this.rating,
        specialties = this.specialties,
        userId = this.userId,
    );
}

fun TutorFavoriteModel.toEntity(): TutorFavoriteEntity {
    return TutorFavoriteEntity(
        id = this.id,
        firstId = this.firstId,
        secondId = this.secondId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    );
}

fun TutorsResponse.toDomain(): List<TutorEntity> {
    return this.tutors.rows.map {
        it.toEntity();
    }
}

fun TutorsResponse.toFavoriteDomain(): TutorFavorites {
    return TutorFavorites(
        count = this.tutors.count,
        tutors = this.tutors.rows.map {
            it.toEntity();
        },
        favoriteTutors = this.favoriteTutor.map {
            it.toEntity();
        }
    )
}

fun TutorModel.toDomain(): TutorEntity {
    return this.toEntity()
}