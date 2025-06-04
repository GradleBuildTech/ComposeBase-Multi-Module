package com.example.domain.usecase.search

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.course.CourseDataSource
import com.example.domain.entity.ContentCategoryEntity
import com.example.domain.entity.course.CourseEntity
import com.example.domain.mapper.toDomain
import com.example.domain.mapper.toRoomCourseEntity
import com.example.local.room.dao.CourseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchUseCaseImpl @Inject constructor(
    private val courseDataSource: CourseDataSource,
    private val courseDao: CourseDao,
    @IODispatcher private val ioDispatcher: CoroutineContext,
) : SearchUseCase {
    override fun fetchCourses(
        paginationRequest: PaginationRequest,
        searchQuery: String?,
        contentCategoryId: String?,
    ): Flow<Either<ExceptionState, List<CourseEntity>>> =
        flow {
            val response = courseDataSource.fetchCourses(
                paginationRequest = paginationRequest,
                searchQuery = searchQuery,
                contentCategoryId = contentCategoryId,
            )
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override fun fetchContentCategories(): Flow<Either<ExceptionState, List<ContentCategoryEntity>>> =
        flow {
            val response = courseDataSource.fetchContentCategories()
            val dataConvert = response.mapAndConverterToStateData {
                it.toDomain()
            }
            emit(dataConvert)
        }

    override suspend fun updateLocalCourse(course: List<CourseEntity>) {
        courseDao.insertAll(course.map { it.toRoomCourseEntity() })
    }
}