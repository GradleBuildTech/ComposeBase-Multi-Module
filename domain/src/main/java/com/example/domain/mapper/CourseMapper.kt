package com.example.domain.mapper

import com.example.data.model.response.course.CourseModel
import com.example.data.model.response.course.CoursesResponse
import com.example.domain.entity.CourseEntity

fun CourseModel.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        level = this.level,
        visible = this.visible,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun CoursesResponse.toDomain(): List<CourseEntity> {
    return this.courses.rows.map {
        it.toEntity()
    }
}