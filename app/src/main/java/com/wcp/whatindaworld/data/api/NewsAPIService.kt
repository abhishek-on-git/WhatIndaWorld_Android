package com.wcp.whatindaworld.data.api

import com.wcp.whatindaworld.BuildConfig
import com.wcp.whatindaworld.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("/v2/top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>

    @GET("/v2/top-headlines")
    suspend fun fetchSearchedHeadlines(
        @Query("country")
        country: String,
        @Query("q")
        searchedQuery: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>
}