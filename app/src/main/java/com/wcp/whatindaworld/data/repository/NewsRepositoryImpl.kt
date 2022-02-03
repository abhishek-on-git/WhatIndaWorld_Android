package com.wcp.whatindaworld.data.repository

import com.wcp.whatindaworld.data.model.APIResponse
import com.wcp.whatindaworld.data.model.Article
import com.wcp.whatindaworld.data.repository.dataSource.NewsLocalDataSource
import com.wcp.whatindaworld.data.repository.dataSource.NewsRemoteDataSource
import com.wcp.whatindaworld.domain.repository.NewsRepository
import com.wcp.whatindaworld.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
): NewsRepository {

    override suspend fun fetchHeadlines(
        country: String,
        page: Int
    ): Resource<APIResponse> {
        val response = remoteDataSource.fetchTopHeadlines(country, page)
        return wrapResponseInResource(response)
    }

    override suspend fun fetchSearchedHeadlines(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        val response = remoteDataSource.fetchSearchedHeadlines(country, searchQuery, page)
        return wrapResponseInResource(response)
    }

    override suspend fun saveToReadLater(article: Article) {
        localDataSource.saveNewsArticleToDB(article)
    }

    override suspend fun deleteFromReadLater(article: Article) {
        TODO("Not yet implemented")
    }

    override fun fetchFromReadLater(): Flow<List<Article>> {
        return localDataSource.fetchSavedArticles()
    }

    private fun wrapResponseInResource(response: Response<APIResponse>): Resource<APIResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}