package com.example.data.dataSource.eBook

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.eBook.EBooksResponse

interface EBookDataSource {
    suspend fun fetchEBooks(
        paginationRequest: PaginationRequest,
    ): DataResponse<EBooksResponse>
}