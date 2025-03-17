package com.example.domain.usecase.home

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.course.CourseDataSource
import com.example.data.dataSource.eBook.EBookDataSource
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.domain.entity.course.CourseEntity
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.tutor.TutorEntity
import com.example.domain.mapper.fromRoomCourseEntity
import com.example.domain.mapper.toDomain
import com.example.domain.mapper.toRoomCourseEntity
import com.example.room.dao.CourseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomeUseCaseImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineContext,
    private val tutorDataSource: TutorDataSource,
    private val courseDataSource: CourseDataSource,
    private val eBookDataSource: EBookDataSource,
    private val courseDao: CourseDao,
) : HomeUseCase {
    override fun fetchTutors(
        paginationRequest: PaginationRequest
    ): Flow<Either<ExceptionState, List<TutorEntity>>> =
        flow {
            val response = tutorDataSource.fetchTutors(paginationRequest = paginationRequest)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override fun fetchRecommendedCourses(
        paginationRequest: PaginationRequest
    ): Flow<Either<ExceptionState, List<CourseEntity>>> =
        flow {
            val getLocalCourse = courseDao.getAll()
            if(getLocalCourse.isEmpty()) {
                val response = courseDataSource.fetchCourses(paginationRequest = paginationRequest)
                val dataConvert = response.mapAndConverterToStateData {
                    it.toDomain()
                }
                if(dataConvert.isRight() && dataConvert.rightValue() != null) {
                    updateLocalCourse(dataConvert.rightValue()!!)
                }
                emit(dataConvert)
            } else {
                emit(Either.Right(getLocalCourse.map { it.fromRoomCourseEntity() }))
            }
        }.flowOn(ioDispatcher)

    override fun fetchEBooks(
        paginationRequest: PaginationRequest
    ): Flow<Either<ExceptionState, List<EBookEntity>>> =
        flow {
            val response = eBookDataSource.fetchEBooks(paginationRequest = paginationRequest)
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override suspend fun updateLocalCourse(course: List<CourseEntity>) {
        courseDao.insertAll(course.map { it.toRoomCourseEntity() })
    }

}
