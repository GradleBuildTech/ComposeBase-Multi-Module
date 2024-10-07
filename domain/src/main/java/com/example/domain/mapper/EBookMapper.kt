package com.example.domain.mapper

import com.example.data.model.response.eBook.EBooksResponse
import com.example.domain.entity.EBookEntity

fun EBooksResponse.toDomain(): List<EBookEntity> {
    return this.eBooks.rows.map {
        EBookEntity(
            id = it.id,
            name = it.name,
            description = it.description,
            imageUrl = it.imageUrl,
            level = it.level,
        );
    }
}