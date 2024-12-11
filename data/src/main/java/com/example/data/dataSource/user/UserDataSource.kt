package com.example.data.dataSource.user

import com.example.core.models.response.DataResponse
import com.example.data.model.response.totalTime.TotalTimeResponse

interface UserDataSource {
    suspend fun fetchTotalTime(): DataResponse<TotalTimeResponse>
}