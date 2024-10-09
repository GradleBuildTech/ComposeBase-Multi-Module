package com.example.domain.entity

class CourseEntity (
    var id: String = "",
    var name: String? = "",
    var description: String? = "",
    var imageUrl: String? = "",
    var level: String? = "",
    var reason: String? = "",
    var purpose: String? = "",
    var otherDetails: String? = "",
    var defaultPrice: Int? = 0,
    var coursePrice: Int? = 0,
    var courseType: String? = "",
    var sectionType: String? = "",
    var visible: Boolean? = false,
    var displayOrder: Int? = 0,
    var createdAt: String? = "",
    var updatedAt: String? = ""
)