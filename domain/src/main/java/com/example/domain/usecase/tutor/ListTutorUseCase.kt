package com.example.domain.usecase.tutor

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.model.request.tutor.SearchTutorRequest
import com.example.domain.entity.TutorEntity
import kotlinx.coroutines.flow.Flow

interface ListTutorUseCase {
    fun fetchTutors(
        paginationRequest: PaginationRequest
    ): Flow<Either<ExceptionState, List<TutorEntity>>>

    fun searchTutors(
        searchTutorRequest: SearchTutorRequest
    ): Flow<Either<ExceptionState, List<TutorEntity>>>

    fun addTutorToFavorite(
        tutorId: String
    ): Flow<Either<ExceptionState, Unit>>
}