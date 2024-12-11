package com.example.data.dataSource.booking

import com.example.core.models.response.DataResponse
import com.example.data.model.response.upcoming.UpComingResponse

interface BookingDataSource {
    suspend fun fetchUpComing(time: Long) : DataResponse<UpComingResponse>
}