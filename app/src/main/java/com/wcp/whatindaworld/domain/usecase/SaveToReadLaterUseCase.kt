package com.wcp.whatindaworld.domain.usecase

import com.wcp.whatindaworld.data.model.Article
import com.wcp.whatindaworld.domain.repository.NewsRepository

class SaveToReadLaterUseCase(
    private val repository: NewsRepository
) {
    suspend fun execute(article: Article) = repository.saveToReadLater(article)
}