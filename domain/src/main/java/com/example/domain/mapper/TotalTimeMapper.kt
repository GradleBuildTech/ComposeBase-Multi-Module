package com.example.domain.mapper

import com.example.data.model.response.totalTime.TotalTimeResponse

fun TotalTimeResponse.toDomain(): Int {
    return this.total
}