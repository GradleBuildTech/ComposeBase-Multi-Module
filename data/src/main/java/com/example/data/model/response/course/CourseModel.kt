package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseModel(
    var id : String,
    var name : String? = null,
    var description : String? = null,
    var imageUrl : String? = null,
    var level : String? = null,
    var reason : String? = null,
    var purpose : String? = null,
    var otherDetails : String? = null,
    var defaultPrice : Int? = null,
    var coursePrice : Int? = null,
    var courseType : String? = null,
    var sectionType : String? = null,
    var visible : Boolean? = null,
    var displayOrder : Int? = null,
    var createdAt : String? = null,
    var updatedAt : String? = null
)