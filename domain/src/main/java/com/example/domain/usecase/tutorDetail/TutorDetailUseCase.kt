package com.example.domain.usecase.tutorDetail

import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.domain.entity.tutor.detail.TutorDetail
import kotlinx.coroutines.flow.Flow

interface TutorDetailUseCase {
    fun getTutorDetail(tutorId: String): Flow<Either<ExceptionState, TutorDetail?>>
}