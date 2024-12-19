package com.example.domain.mapper

import com.example.data.model.response.course.ContentCategoryModel
import com.example.data.model.response.course.ContentCategoryResponse
import com.example.data.model.response.course.CourseDetailResponse
import com.example.data.model.response.course.CourseModel
import com.example.data.model.response.course.CoursesResponse
import com.example.data.model.response.course.TopicModel
import com.example.domain.entity.ContentCategoryEntity
import com.example.domain.entity.CourseEntity
import com.example.domain.entity.TopicEntity

fun String.toExperienceText(): String {
    return when (this) {
        "0" -> "Any level"
        "1" -> "Beginner"
        "2" -> "High Beginner"
        "3" -> "Pre-Intermediate"
        "4" -> "Intermediate"
        "5" -> "Upper-Intermediate"
        "6" -> "Advanced"
        else -> "Proficiency"
    }
}

fun TopicModel.toEntity(): TopicEntity {
    return TopicEntity(
        id = this.id,
        courseId = this.courseId,
        orderCourse = this.orderCourse,
        name = this.name,
        beforeTheClassNotes = this.beforeTheClassNotes,
        afterTheClassNotes = this.afterTheClassNotes,
        nameFile = this.nameFile,
        numberOfPages = this.numberOfPages,
        description = this.description,
        videoUrl = this.videoUrl,
        type = this.type,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun ContentCategoryModel.toEntity(): ContentCategoryEntity {
    return ContentCategoryEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        key = this.key,
        displayOrder = this.displayOrder,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun CourseModel.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        level = this.level,
        visible = this.visible,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        reason = this.reason,
        purpose = this.purpose,
        topics = this.topics?.map {
            it.toEntity()
        } ?: emptyList()
    )
}

fun ContentCategoryResponse.toDomain(): List<ContentCategoryEntity> {
    return this.rows.reversed().map {
        it.toEntity()
    }
}

fun CoursesResponse.toDomain(): List<CourseEntity> {
    return this.courses.rows.map {
        it.toEntity()
    }
}

fun CourseDetailResponse.toDomain(): CourseEntity {
    return this.data.toEntity()
}