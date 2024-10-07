package com.example.domain.mapper

import com.example.data.model.response.course.CoursesResponse
import com.example.domain.entity.CourseEntity

fun CoursesResponse.toDomain(): List<CourseEntity> {
    return this.courses.rows.map {
        CourseEntity(
            id = it.id,
            name = it.name,
            description = it.description,
            imageUrl = it.imageUrl,
            level = it.level,
        );
    }
}