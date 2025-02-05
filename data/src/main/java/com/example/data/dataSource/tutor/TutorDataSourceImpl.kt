package com.example.data.dataSource.tutor

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.tutor.AddFavoriteTutorResponse
import com.example.data.model.response.tutor.TutorDetailResponse
import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class TutorDataSourceImpl @Inject constructor(
    private val tutorApi: TutorApi
) : TutorDataSource {
    override suspend fun fetchTutors(
        paginationRequest: PaginationRequest
    ): DataResponse<TutorsResponse> {
        return handleCall { tutorApi.fetchTutors(page = paginationRequest.page, pageSize = paginationRequest.pageSize) }
    }

    override suspend fun addFavoriteTutor(tutorId: String): DataResponse<AddFavoriteTutorResponse> {
        return handleCall { tutorApi.addFavoriteTutor(mapOf("tutorId" to tutorId)) }
    }

    override suspend fun getTutorDetail(tutorId: String): DataResponse<TutorModel> {
        return handleCall { tutorApi.fetchTutorDetail(id = tutorId) }
    }
}