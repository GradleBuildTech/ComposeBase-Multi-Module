package com.example.feat.home.model

import com.example.feat.home.entity.CourseEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseModel(
    var id : String,
    var name : String?,
    var description : String?,
    var imageUrl : String?,
    var level : String?,
    var reason : String?,
    var purpose : String?,
    var otherDetails : String?,
    var defaultPrice : Int?,
    var coursePrice : Int?,
    var courseType : String?,
    var sectionType : String?,
    var visible : Boolean?,
    var displayOrder : Int?,
    var createdAt : String?,
    var updatedAt : String?
) {
    fun toEntity(): CourseEntity {
        return CourseEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            imageUrl = this.imageUrl,
            level = this.level,
            reason = this.reason,
            purpose = this.purpose,
            otherDetails = this.otherDetails,
            defaultPrice = this.defaultPrice,
            coursePrice = this.coursePrice,
            courseType = this.courseType,
            sectionType = this.sectionType,
            visible = this.visible,
            displayOrder = this.displayOrder,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}