package com.example.domain.entity

data class BookingInfoEntity(
    var id: String = "",
    var userId: String? = "",
    var scheduleId: String? = "",
    var cancelReasonId: String? = "",
    var lessonPlanId: String? = "",
    var calendarId: String? = "",
    var cancelNote: String? = "",
    var isDeleted: Boolean? = false,
    var isTrial: Boolean? = false,
    var convertedLesson: Int? = 0,
    var tutorMeetingLink: String? = "",
    var studentMeetingLink: String? = "",
    var googleMeetLink: String? = "",
    var studentRequest: String? = "",
    var tutorReview: String? = "",
    var scoreByTutor: String? = "",
    var recordUrl: String? = "",
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var createdAtTimeStamp: Long? = 0,
    var updatedAtTimeStamp: Long? = 0,
    var scheduleDetailEntity:  ScheduleDetailEntity? = null
)