package com.wcp.whatindaworld.domain.usecase

import com.wcp.whatindaworld.data.model.Article
import com.wcp.whatindaworld.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class FetchFromReadLaterUseCase(
    private val repository: NewsRepository
) {
    suspend fun execute(): Flow<List<Article>> = repository.fetchFromReadLater()
}