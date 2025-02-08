package com.example.domain.usecase.document

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.booking.BookingDataSource
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.data.dataSource.user.UserDataSource
import com.example.domain.entity.BookingInfoEntity
import com.example.domain.entity.tutor.TutorFavorites
import com.example.domain.mapper.toDomain
import com.example.domain.mapper.toFavoriteDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DocumentUseCaseImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineContext,
    private val bookingDataSource: BookingDataSource,
    private val userDataSource: UserDataSource,
    private val tutorDataSource: TutorDataSource,
) : DocumentUseCase {
    override fun fetchUpComing(time: Long): Flow<Either<ExceptionState, BookingInfoEntity>> =
        flow {
            val response = bookingDataSource.fetchUpComing(time)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override fun fetchTotalTime(): Flow<Either<ExceptionState, Int>> =
        flow {
            val response = userDataSource.fetchTotalTime()
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override fun fetchTutors(paginationRequest: PaginationRequest): Flow<Either<ExceptionState, TutorFavorites>> =
        flow {
            val response = tutorDataSource.fetchTutors(paginationRequest = paginationRequest)
            val dataConvert = response.mapAndConverterToStateData {
                it.toFavoriteDomain()
            }
            emit(dataConvert)
        }

    override fun addFavoriteTutor(tutorId: String): Flow<Either<ExceptionState, Boolean>> = flow {
        val response = tutorDataSource.addFavoriteTutor(tutorId)

        val failedResponseCode = 1.0

        val dataConvert = response.mapAndConverterToStateData {
            it.result != failedResponseCode
        }

        emit(dataConvert)
    }
}