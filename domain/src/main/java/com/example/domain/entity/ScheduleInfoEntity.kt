package com.example.domain.entity

data class ScheduleInfoEntity(
    var id: String,
    var tutorId: String,
    var date: String,
    var startTime: String,
    var endTime: String,
    var startTimestamp: Long,
    var endTimestamp: Long,
    var isDeleted: Boolean,
    var createdAt: String,
    var updatedAt: String,
    var tutorInfo: TutorEntity
)