package com.example.data.model.response.eBook

import com.example.data.model.PaginationData
import com.example.data.model.response.tutor.TutorModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EBooksResponse(
    @Json(name = "data") val eBooks: PaginationData<EBookModel>
)