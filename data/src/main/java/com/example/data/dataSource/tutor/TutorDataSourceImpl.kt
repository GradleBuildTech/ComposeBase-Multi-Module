package com.example.data.dataSource.tutor

import com.example.core.models.pagination.Pagination
import com.example.core.models.response.DataResponse
import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class TutorDataSourceImpl @Inject constructor(
    private val tutorApi: TutorApi
) : TutorDataSource {
    override suspend fun fetchTutors(
        page: Int,
        pageSize: Int
    ): DataResponse<TutorsResponse> {
        return handleCall { tutorApi.fetchTutors(page = page, pageSize = pageSize) }
    }
}