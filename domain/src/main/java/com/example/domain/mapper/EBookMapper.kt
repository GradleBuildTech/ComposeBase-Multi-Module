package com.example.domain.mapper

import com.example.data.model.response.eBook.EBookModel
import com.example.data.model.response.eBook.EBooksResponse
import com.example.domain.entity.EBookEntity
//import com.example.room.entities.RoomCourseEntity
//import com.example.room.entities.RoomEBookEntity

fun EBookModel.toEntity(): EBookEntity {
    return EBookEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        level = this.level,
    )
}

fun EBooksResponse.toDomain(): List<EBookEntity> {
    return this.eBooks.rows.map {
        it.toEntity()
    }
}

//fun EBookEntity.toRoomEBookEntity(): RoomEBookEntity {
//    return RoomEBookEntity(
//        id = this.id,
//        name = this.name,
//        description = this.description,
//        imageUrl = this.imageUrl,
//        level = this.level,
//    )
//}
//
//
//fun RoomEBookEntity.fromRoomEBookEntity(): EBookEntity {
//    return EBookEntity(
//        id = this.id,
//        name = this.name,
//        description = this.description,
//        imageUrl = this.imageUrl,
//        level = this.level,
//    )
//}
//
