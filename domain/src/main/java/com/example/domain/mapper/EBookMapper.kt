package com.example.domain.mapper

import com.example.data.model.response.eBook.EBookModel
import com.example.data.model.response.eBook.EBooksResponse
import com.example.domain.entity.EBookEntity

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