package com.example.data.model.response.upcoming

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpComingResponse(
    var message: String? = null,
    var data: List<BookingInfoModel>? = null
)