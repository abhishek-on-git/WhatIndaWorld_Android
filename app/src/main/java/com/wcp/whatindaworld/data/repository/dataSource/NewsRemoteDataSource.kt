package com.wcp.whatindaworld.data.repository.dataSource

import com.wcp.whatindaworld.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun fetchTopHeadlines(
        country: String,
        page: Int
    ): Response<APIResponse>

    suspend fun fetchSearchedHeadlines(
        country: String,
        searchedQuery: String,
        page: Int
    ): Response<APIResponse>
}