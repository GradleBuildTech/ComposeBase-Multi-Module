package com.example.data.dataSource.tutor

import com.example.core.models.pagination.Pagination
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.request.tutor.SearchTutorRequest
import com.example.data.model.response.tutor.SearchTutorResponse
import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
import com.example.data.model.response.tutor.tutorDetail.TutorDetailModel
import com.example.network.extensions.handleCall
import javax.inject.Inject

class TutorDataSourceImpl @Inject constructor(
    private val tutorApi: TutorApi
) : TutorDataSource {
    override suspend fun fetchTutors(
        paginationRequest: PaginationRequest
    ): DataResponse<TutorsResponse> {
        return handleCall {
            tutorApi.fetchTutors(
                page = paginationRequest.page,
                pageSize = paginationRequest.pageSize
            )
        }
    }

    override suspend fun searchTutors(searchTutorRequest: SearchTutorRequest): DataResponse<SearchTutorResponse> {
        return handleCall {
            var filterResult = mapOf<String, Any>();
            if (searchTutorRequest.topics != null) {
                filterResult = filterResult.plus("specialties" to searchTutorRequest.topics)
            }
            if (searchTutorRequest.nationality != null){
                filterResult = filterResult.plus("nationality" to searchTutorRequest.nationality)
            }
            tutorApi.searchTutors(
                page = searchTutorRequest.page,
                perPage = searchTutorRequest.perPage,
                search = searchTutorRequest.search,
                filter = filterResult
            )
        }
    }

    override suspend fun getTutorById(tutorId: String): DataResponse<TutorDetailModel> {
        return handleCall {
            tutorApi.getTutorById(tutorId)
        }
    }

    override suspend fun addTutorToFavorite(tutorId: String): DataResponse<Unit> {
        return handleCall {
            tutorApi.addTutorToFavorite(mapOf("tutorId" to tutorId))
        }
    }
}