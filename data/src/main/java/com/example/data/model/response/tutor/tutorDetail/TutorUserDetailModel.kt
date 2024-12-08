package com.example.data.model.response.tutor.tutorDetail

import com.example.data.model.response.course.CoursePreviewModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TutorUserDetailModel(
    var id : String,
    var level : String? = null,
    var avatar : String?= null,
    var name : String?= null,
    var country : String?= null,
    var language : String?= null,
    var isPublicRecord : Boolean?= null,
    var caredByStaffId : String?= null,
    var studentGroupId : String?= null,
    var zaloUserId : String?= null,
    var courses: List<CoursePreviewModel>
)