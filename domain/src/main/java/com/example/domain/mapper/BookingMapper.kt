package com.example.domain.mapper

import com.example.data.model.response.upcoming.BookingInfoModel
import com.example.data.model.response.upcoming.ScheduleDetailModel
import com.example.data.model.response.upcoming.ScheduleInfoModel
import com.example.data.model.response.upcoming.UpComingResponse
import com.example.domain.entity.BookingInfoEntity
import com.example.domain.entity.ScheduleDetailEntity
import com.example.domain.entity.ScheduleInfoEntity

fun ScheduleInfoModel.toEntity(): ScheduleInfoEntity {
    return ScheduleInfoEntity(
        id = this.id,
        tutorId = this.tutorId,
        date = this.date,
        startTime = this.startTime,
        endTime = this.endTime,
        startTimestamp = this.startTimestamp,
        endTimestamp = this.endTimestamp,
        isDeleted = this.isDeleted,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        tutorInfo = this.tutorInfo.toEntity(),
    )
}

fun ScheduleDetailModel.toEntity(): ScheduleDetailEntity {
    return ScheduleDetailEntity(
        id = this.id,
        scheduleId = this.scheduleId,
        startPeriod = this.startPeriod,
        endPeriod = this.endPeriod,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        startPeriodTimestamp = this.startPeriodTimestamp,
        endPeriodTimestamp = this.endPeriodTimestamp,
        scheduleInfo = this.scheduleInfo?.toEntity()
    )
}

fun BookingInfoModel.toEntity(): BookingInfoEntity {
    return BookingInfoEntity(
        id = this.id,
        userId = this.userId,
        scheduleId = this.scheduleDetailId,
        cancelReasonId = this.cancelReasonId,
        lessonPlanId = this.lessonPlanId,
        calendarId = this.calendarId,
        cancelNote = this.cancelNote,
        isDeleted = this.isDeleted,
        isTrial = this.isTrial,
        convertedLesson = this.convertedLesson,
        tutorMeetingLink = this.tutorMeetingLink,
        studentMeetingLink = this.studentMeetingLink,
        googleMeetLink = this.googleMeetLink,
        studentRequest = this.studentRequest,
        tutorReview = this.tutorReview,
        scoreByTutor = this.scoreByTutor,
        recordUrl = this.recordUrl,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        createdAtTimeStamp = this.createdAtTimeStamp,
        updatedAtTimeStamp = this.updatedAtTimeStamp,
        scheduleDetailEntity = this.scheduleDetailModel?.toEntity()
    )
}

fun UpComingResponse.toDomain(): BookingInfoEntity {
    return this.data?.map { it.toEntity() }?.first() ?: BookingInfoEntity()
}