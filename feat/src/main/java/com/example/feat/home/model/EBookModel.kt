package com.example.feat.home.model

import com.example.feat.home.entity.EBookEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EBookModel(
    var id : String,
    var name : String?,
    var description : String?,
    var imageUrl : String?,
    var level : String?,
    var visible : Boolean?,
    var fileUrl : String?,
    var createdAt : String?,
    var updatedAt : String?
) {
    fun toEntity(): EBookEntity {
        return EBookEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            imageUrl = this.imageUrl,
            level = this.level,
            visible = this.visible,
            fileUrl = this.fileUrl,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}