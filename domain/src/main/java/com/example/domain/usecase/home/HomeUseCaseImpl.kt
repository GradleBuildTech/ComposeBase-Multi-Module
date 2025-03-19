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
import com.example.domain.mapper.fromRoomEBookEntity
import com.example.domain.mapper.fromRoomTutorEntity
import com.example.domain.mapper.toDomain
import com.example.domain.mapper.toRoomCourseEntity
import com.example.domain.mapper.toRoomEBookEntity
import com.example.domain.mapper.toRoomTutorEntity
import com.example.room.dao.CourseDao
import com.example.room.dao.EBookDao
import com.example.room.dao.TutorDao
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
    private val tutorDao: TutorDao,
    private val eBookDao: EBookDao,
) : HomeUseCase {
    override fun fetchTutors(
        paginationRequest: PaginationRequest
    ): Flow<Either<ExceptionState, List<TutorEntity>>> =
        flow {
            val getLocalTutor = tutorDao.getAll()
            if(getLocalTutor.isEmpty()) {
                val response = tutorDataSource.fetchTutors(paginationRequest = paginationRequest)
                val dataConvert = response.mapAndConverterToStateData {
                    it.toDomain()
                }
                if(dataConvert.isRight() && dataConvert.rightValue() != null) {
                    updateLocalTutor(dataConvert.rightValue()!!)
                }
                emit(dataConvert)
            } else {
                emit(Either.Right(getLocalTutor.map { it.fromRoomTutorEntity() }))
            }
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
            val getLocalEBook = eBookDao.getAll()
            if(getLocalEBook.isEmpty()) {
                val response = eBookDataSource.fetchEBooks(paginationRequest = paginationRequest)
                val dataConvert = response.mapAndConverterToStateData {
                    it.toDomain()
                }
                if(dataConvert.isRight() && dataConvert.rightValue() != null) {
                    updateLocalEBook(dataConvert.rightValue()!!)
                }
                emit(dataConvert)
            } else {
                emit(Either.Right(getLocalEBook.map { it.fromRoomEBookEntity() }))
            }
        }

    override suspend fun updateLocalCourse(course: List<CourseEntity>) {
        courseDao.insertAll(course.map { it.toRoomCourseEntity() })
    }

    override suspend fun updateLocalTutor(tutor: List<TutorEntity>) {
        tutorDao.insertAll(tutor.map { it.toRoomTutorEntity() })
    }

    override suspend fun updateLocalEBook(eBook: List<EBookEntity>) {
        eBookDao.insertAll(eBook.map { it.toRoomEBookEntity() })
    }

}
