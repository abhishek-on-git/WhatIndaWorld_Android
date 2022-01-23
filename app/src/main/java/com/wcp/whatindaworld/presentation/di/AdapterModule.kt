package com.wcp.whatindaworld.presentation.di

import com.wcp.whatindaworld.presentation.adapter.HeadlinesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideHeadlinesAdapter(): HeadlinesAdapter{
        return HeadlinesAdapter()
    }
}