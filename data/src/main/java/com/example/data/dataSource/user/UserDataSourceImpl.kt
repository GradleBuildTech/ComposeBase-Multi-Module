package com.example.data.dataSource.user

import com.example.core.models.response.DataResponse
import com.example.data.model.response.totalTime.TotalTimeResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserDataSource {
    override suspend fun fetchTotalTime(): DataResponse<TotalTimeResponse> {
        return handleCall { userApi.getTotalTime() }
    }
}