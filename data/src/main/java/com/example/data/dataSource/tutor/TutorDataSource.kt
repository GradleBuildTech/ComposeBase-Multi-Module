package com.example.data.dataSource.tutor

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.tutor.AddFavoriteTutorResponse
import com.example.data.model.response.tutor.TutorsResponse
import com.example.data.model.response.tutor.detail.TutorDetailModel

interface TutorDataSource {
    suspend fun fetchTutors(
        paginationRequest: PaginationRequest,
    ): DataResponse<TutorsResponse>
    suspend fun addFavoriteTutor(
        tutorId: String,
    ): DataResponse<AddFavoriteTutorResponse>

    suspend fun getTutorDetail(
        tutorId: String,
    ): DataResponse<TutorDetailModel>
}