package com.example.domain.usecase.tutorDetail

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.domain.entity.TutorEntity
import com.example.domain.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TutorDetailUseCaseImpl @Inject constructor(
    private val tutorDataSource: TutorDataSource,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : TutorDetailUseCase {

    override fun getTutorDetail(tutorId: String): Flow<Either<ExceptionState, TutorEntity?>> =
        flow {
            val response = tutorDataSource.getTutorDetail(tutorId)
            val dataConvert = response.mapAndConverterToStateData { it.toDomain() }
            emit(dataConvert)
        }
}