package com.example.data.dataSource.booking

import com.example.core.models.response.DataResponse
import com.example.data.model.response.upcoming.UpComingResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class BookingDataSourceImpl @Inject constructor(
    private val bookingApi: BookingApi
) : BookingDataSource{
    override suspend fun fetchUpComing(time: Long): DataResponse<UpComingResponse> {
        return handleCall { bookingApi.getUpComing(time = time) }
    }
}