package com.example.data.model.response.eBook

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EBookModel(
    var id : String,
    var name : String? = null,
    var description : String? = null,
    var imageUrl : String? = null,
    var level : String? = null,
    var visible : Boolean? = null,
    var fileUrl : String? = null,
    var createdAt : String? = null,
    var updatedAt : String? = null
)