package com.example.domain.usecase.home

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.course.CourseDataSource
import com.example.data.dataSource.eBook.EBookDataSource
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.domain.entity.CourseEntity
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.TutorEntity
import com.example.domain.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomeUseCaseImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineContext,
    private val tutorDataSource: TutorDataSource,
    private val courseDataSource: CourseDataSource,
    private val eBookDataSource: EBookDataSource,
) : HomeUseCase {
    override fun fetchTutors(
        page: Int,
        pageSize: Int
    ): Flow<Either<ExceptionState, List<TutorEntity>>> =
        flow {
            val response = tutorDataSource.fetchTutors(page = page, pageSize = pageSize)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override fun fetchRecommendedCourses(
        page: Int,
        pageSize: Int
    ): Flow<Either<ExceptionState, List<CourseEntity>>> =
        flow {
            val response = courseDataSource.fetchCourses(page = page, pageSize = pageSize)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override fun fetchEBooks(
        page: Int,
        pageSize: Int
    ): Flow<Either<ExceptionState, List<EBookEntity>>> =
        flow {
            val response = eBookDataSource.fetchEBooks(page = page, pageSize = pageSize)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

}
