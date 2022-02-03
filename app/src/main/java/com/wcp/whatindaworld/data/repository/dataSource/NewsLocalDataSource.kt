package com.wcp.whatindaworld.data.repository.dataSource

import com.wcp.whatindaworld.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveNewsArticleToDB(article: Article)

    fun fetchSavedArticles(): Flow<List<Article>>
}