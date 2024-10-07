package com.example.domain.usecase.home

import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.domain.entity.CourseEntity
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.TutorEntity
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun fetchTutors(
        page: Int,
        pageSize: Int,
    ): Flow<Either<ExceptionState, List<TutorEntity>>>

    fun fetchRecommendedCourses(
        page: Int,
        pageSize: Int,
    ): Flow<Either<ExceptionState, List<CourseEntity>>>

    fun fetchEBooks(
        page: Int,
        pageSize: Int,
    ): Flow<Either<ExceptionState, List<EBookEntity>>>
}