package com.wcp.whatindaworld.presentation.di

import com.wcp.whatindaworld.domain.repository.NewsRepository
import com.wcp.whatindaworld.domain.usecase.FetchFromReadLaterUseCase
import com.wcp.whatindaworld.domain.usecase.FetchHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.FetchSearchedHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.SaveToReadLaterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchHeadlinesUseCase(
        repository: NewsRepository
    ): FetchHeadlinesUseCase {
        return FetchHeadlinesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSearchedHeadlinesUseCase(
        repository: NewsRepository
    ): FetchSearchedHeadlinesUseCase {
        return FetchSearchedHeadlinesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveToReadLaterUseCase(
        repository: NewsRepository
    ): SaveToReadLaterUseCase {
        return SaveToReadLaterUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideFetchFromReadLaterUseCase(
        repository: NewsRepository
    ): FetchFromReadLaterUseCase {
        return FetchFromReadLaterUseCase(repository)
    }
}