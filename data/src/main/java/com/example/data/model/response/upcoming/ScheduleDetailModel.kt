package com.example.data.model.response.upcoming

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleDetailModel(
    var id: String,
    var scheduleId: String,
    var startPeriod: String,
    var endPeriod: String,
    var createdAt: String,
    var updatedAt: String,
    var startPeriodTimestamp: Long,
    var endPeriodTimestamp: Long,
    var scheduleInfo: ScheduleInfoModel? = null,
)