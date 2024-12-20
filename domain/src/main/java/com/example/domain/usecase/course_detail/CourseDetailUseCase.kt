package com.example.domain.usecase.course_detail

import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.domain.entity.CourseEntity
import com.example.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface CourseDetailUseCase {
    fun fetchCourseDetail(id: String) : Flow<Either<ExceptionState, CourseEntity?>>
}