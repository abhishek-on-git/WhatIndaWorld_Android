package com.wcp.whatindaworld.data.repository.dataSourceImpl

import com.wcp.whatindaworld.data.api.NewsAPIService
import com.wcp.whatindaworld.data.model.APIResponse
import com.wcp.whatindaworld.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val apiService: NewsAPIService
) : NewsRemoteDataSource {

    override suspend fun fetchTopHeadlines(
        country: String,
        page: Int
    ): Response<APIResponse> = apiService.fetchTopHeadlines(country, page)

    override suspend fun fetchSearchedHeadlines(
        country: String,
        searchedQuery: String,
        page: Int
    ): Response<APIResponse> = apiService.fetchSearchedHeadlines(country, searchedQuery, page)
}