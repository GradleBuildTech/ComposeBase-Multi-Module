package com.example.data.model.response.totalTime

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TotalTimeResponse(
    var total: Int = 0
)