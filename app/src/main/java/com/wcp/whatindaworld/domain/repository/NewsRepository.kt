package com.wcp.whatindaworld.domain.repository

import androidx.lifecycle.LiveData
import com.wcp.whatindaworld.data.model.APIResponse
import com.wcp.whatindaworld.data.model.Article
import com.wcp.whatindaworld.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchHeadlines(
        country: String,
        page: Int
    ): Resource<APIResponse>
    suspend fun fetchSearchedHeadlines(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse>
    suspend fun saveToReadLater(article: Article)
    suspend fun deleteFromReadLater(article: Article)
    fun fetchFromReadLater(): Flow<List<Article>>
}