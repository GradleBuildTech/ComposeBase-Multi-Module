package com.example.data.model.response.upcoming

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookingInfoModel(
    var id: String,
    var userId: String? = null,
    var scheduleDetailId: String? = null,
    var cancelReasonId: String? = null,
    var lessonPlanId: String? = null,
    var calendarId: String? = null,
    var cancelNote: String? = null,
    var isDeleted: Boolean? = null,
    var isTrial: Boolean? = null,
    var convertedLesson: Int? = null,
    var tutorMeetingLink: String? = null,
    var studentMeetingLink: String? = null,
    var googleMeetLink: String? = null,
    var studentRequest: String? = null,
    var tutorReview: String? = null,
    var scoreByTutor: String? = null,
    var recordUrl: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var createdAtTimeStamp: Long? = null,
    var updatedAtTimeStamp: Long? = null,
    @Json(name = "scheduleDetailInfo") var scheduleDetailModel: ScheduleDetailModel? = null
)