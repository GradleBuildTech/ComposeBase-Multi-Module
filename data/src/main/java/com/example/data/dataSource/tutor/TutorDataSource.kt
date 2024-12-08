package com.example.data.dataSource.tutor

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.request.tutor.SearchTutorRequest
import com.example.data.model.response.tutor.SearchTutorResponse
import com.example.data.model.response.tutor.TutorsResponse
import com.example.data.model.response.tutor.tutorDetail.TutorDetailModel

interface TutorDataSource {
    suspend fun fetchTutors(
        paginationRequest: PaginationRequest,
    ): DataResponse<TutorsResponse>

    suspend fun searchTutors(
        searchTutorRequest: SearchTutorRequest
    ): DataResponse<SearchTutorResponse>

    suspend fun getTutorById(
        tutorId: String
    ): DataResponse<TutorDetailModel>

    suspend fun addTutorToFavorite(
        tutorId: String
    ): DataResponse<Unit>
}