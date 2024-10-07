package com.example.data.dataSource.tutor

import com.example.core.models.response.DataResponse
import com.example.data.model.response.tutor.TutorsResponse

interface TutorDataSource {
    suspend fun fetchTutors(
        page: Int, pageSize: Int,
    ): DataResponse<TutorsResponse>
}