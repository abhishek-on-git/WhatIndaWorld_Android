package com.wcp.whatindaworld.presentation.di

import com.wcp.whatindaworld.domain.repository.NewsRepository
import com.wcp.whatindaworld.domain.usecase.FetchHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.FetchSearchedHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideFetchHeadlinesUseCase(
        repository: NewsRepository
    ): FetchHeadlinesUseCase {
        return FetchHeadlinesUseCase(repository)
    }

    @Provides
    fun provideSearchedHeadlinesUseCase(
        repository: NewsRepository
    ): FetchSearchedHeadlinesUseCase {
        return FetchSearchedHeadlinesUseCase(repository)
    }
}