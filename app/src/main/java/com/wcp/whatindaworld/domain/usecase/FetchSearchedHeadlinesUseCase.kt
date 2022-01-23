package com.wcp.whatindaworld.domain.usecase

import com.wcp.whatindaworld.data.model.APIResponse
import com.wcp.whatindaworld.domain.repository.NewsRepository
import com.wcp.whatindaworld.util.Resource

class FetchSearchedHeadlinesUseCase(
    private val repository: NewsRepository
) {
    suspend fun execute(
        country: String,
        searchedQuery: String,
        page: Int
    ): Resource<APIResponse> = repository.fetchSearchedHeadlines(country, searchedQuery, page)
}