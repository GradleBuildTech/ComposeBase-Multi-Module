package com.example.domain.usecase.course_detail

import com.example.core.lib.utils.IODispatcher
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.data.dataSource.course.CourseDataSource
import com.example.domain.entity.course.CourseEntity
import com.example.domain.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CourseDetailUseCaseImpl @Inject constructor(
    private val courseDataSource: CourseDataSource,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : CourseDetailUseCase {
    override fun fetchCourseDetail(id: String): Flow<Either<ExceptionState, CourseEntity?>> = flow {
        val response = courseDataSource.fetchCourseDetail(id)
        val dataConvert = response.mapAndConverterToStateData { it.toDomain() }
        emit(dataConvert)
    }
}