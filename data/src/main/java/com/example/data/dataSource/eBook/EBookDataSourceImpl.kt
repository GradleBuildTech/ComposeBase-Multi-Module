package com.example.data.dataSource.eBook

import com.example.core.models.response.DataResponse
import com.example.data.model.response.eBook.EBooksResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class EBookDataSourceImpl @Inject constructor(
    private val api: EBookApi,
) : EBookDataSource{
    override suspend fun fetchEBooks(page: Int, pageSize: Int): DataResponse<EBooksResponse> {
        return handleCall { api.fetchEBooks(page = page, pageSize = pageSize)}
    }
}