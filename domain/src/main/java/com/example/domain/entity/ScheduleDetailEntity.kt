package com.example.domain.entity

data class ScheduleDetailEntity(
    var id: String,
    var scheduleId: String,
    var startPeriod: String,
    var endPeriod: String,
    var createdAt: String,
    var updatedAt: String,
    var startPeriodTimestamp: Long,
    var endPeriodTimestamp: Long,
    var scheduleInfo: ScheduleInfoEntity? = null,
)
