package com.wcp.whatindaworld.presentation.di

import com.wcp.whatindaworld.data.db.NewsArticleDao
import com.wcp.whatindaworld.data.repository.dataSource.NewsLocalDataSource
import com.wcp.whatindaworld.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articleDao: NewsArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}