package com.example.domain.usecase.document

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.domain.entity.BookingInfoEntity
import com.example.domain.entity.tutor.TutorFavorites
import kotlinx.coroutines.flow.Flow

interface DocumentUseCase {
    fun fetchUpComing(time : Long) : Flow<Either<ExceptionState, BookingInfoEntity>>
    fun fetchTotalTime() : Flow<Either<ExceptionState, Int>>
    fun fetchTutors(
        paginationRequest: PaginationRequest
    ): Flow<Either<ExceptionState, TutorFavorites>>
    fun addFavoriteTutor(tutorId: String): Flow<Either<ExceptionState, Boolean>>
}