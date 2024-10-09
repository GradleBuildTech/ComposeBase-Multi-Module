package com.example.data.dataSource.eBook

import com.example.data.model.response.eBook.EBooksResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EBookApi {
    @GET(ApiPath.EBOOK)
    suspend fun fetchEBooks(
        @Query("page") page: Int,
        @Query("perPage") pageSize: Int
    ): Response<EBooksResponse>
}