package com.wcp.whatindaworld.presentation.di

import com.wcp.whatindaworld.data.api.NewsAPIService
import com.wcp.whatindaworld.data.repository.dataSource.NewsRemoteDataSource
import com.wcp.whatindaworld.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        apiService: NewsAPIService
    ): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(apiService)
    }

}