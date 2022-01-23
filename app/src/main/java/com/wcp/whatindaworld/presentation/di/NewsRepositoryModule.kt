package com.wcp.whatindaworld.presentation.di

import com.wcp.whatindaworld.data.repository.NewsRepositoryImpl
import com.wcp.whatindaworld.data.repository.dataSource.NewsRemoteDataSource
import com.wcp.whatindaworld.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsRepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource)
    }
}