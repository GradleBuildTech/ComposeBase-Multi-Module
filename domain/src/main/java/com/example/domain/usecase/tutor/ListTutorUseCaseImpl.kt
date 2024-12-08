package com.example.domain.usecase.tutor

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.data.model.request.tutor.SearchTutorRequest
import com.example.domain.entity.TutorEntity
import com.example.domain.mapper.toDomain
import com.example.domain.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ListTutorUseCaseImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineContext,
    private val tutorDataSource: TutorDataSource
) : ListTutorUseCase {
    override fun fetchTutors(paginationRequest: PaginationRequest): Flow<Either<ExceptionState, List<TutorEntity>>> {
        return flow {
            val response = tutorDataSource.fetchTutors(paginationRequest = paginationRequest)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }
    }

    override fun searchTutors(searchTutorRequest: SearchTutorRequest): Flow<Either<ExceptionState, List<TutorEntity>>> {
        return flow {
            val response = tutorDataSource.searchTutors(searchTutorRequest = searchTutorRequest)
            val dataConvert = response.mapAndConverterToStateData {
                it.tutors.map { tutor ->
                    tutor.toEntity()
                }
            }
            emit(dataConvert)
        }
    }

    override fun addTutorToFavorite(tutorId: String): Flow<Either<ExceptionState, Unit>> {
        return flow {
            val response = tutorDataSource.addTutorToFavorite(tutorId = tutorId)
            val dataConvert = response.mapAndConverterToStateData {}
            emit(dataConvert)
        }
    }
}