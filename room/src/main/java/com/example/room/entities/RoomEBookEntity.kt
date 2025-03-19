package com.example.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ebook")
data class RoomEBookEntity(
    @PrimaryKey val id: String,
    var name : String? = "",
    var description : String? = "",
    var imageUrl : String? = "",
    var level : String? = "",
    var visible : Boolean? = false,
    var fileUrl : String? = "",
    var createdAt : String? = "",
    var updatedAt : String? = ""
)