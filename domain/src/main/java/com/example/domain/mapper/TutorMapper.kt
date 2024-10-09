package com.example.domain.mapper

import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
import com.example.domain.entity.TutorEntity

fun TutorModel.toEntity() : TutorEntity {
    return TutorEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        bio = this.bio,
        avatar = this.avatar,
        level = this.level,
        country = this.country,
    );
}

fun TutorsResponse.toDomain(): List<TutorEntity> {
    return this.tutors.rows.map {
        it.toEntity();
    }
}