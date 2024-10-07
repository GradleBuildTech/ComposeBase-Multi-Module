package com.example.domain.mapper

import com.example.data.model.response.tutor.TutorsResponse
import com.example.domain.entity.TutorEntity

fun TutorsResponse.toDomain(): List<TutorEntity> {
    return this.tutors.rows.map {
        TutorEntity(
            id = it.id,
            name = it.name,
            email = it.email,
            bio = it.bio,
            avatar = it.avatar,
            level = it.level,
            country = it.country,
        );
    }
}