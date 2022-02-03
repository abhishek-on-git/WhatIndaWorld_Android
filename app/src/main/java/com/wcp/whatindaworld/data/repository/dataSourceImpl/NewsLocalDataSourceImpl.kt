package com.wcp.whatindaworld.data.repository.dataSourceImpl

import com.wcp.whatindaworld.data.db.NewsArticleDao
import com.wcp.whatindaworld.data.model.Article
import com.wcp.whatindaworld.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow


class NewsLocalDataSourceImpl(
    val articleDao: NewsArticleDao
): NewsLocalDataSource {
    override suspend fun saveNewsArticleToDB(article: Article) {
        articleDao.insert(article)
    }

    override fun fetchSavedArticles(): Flow<List<Article>> {
        return articleDao.fetchSaveArticles()
    }
}