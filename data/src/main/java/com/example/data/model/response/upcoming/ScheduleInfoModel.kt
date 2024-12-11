package com.example.data.model.response.upcoming

import com.example.data.model.response.tutor.TutorModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleInfoModel(
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
    var tutorInfo: TutorModel
)