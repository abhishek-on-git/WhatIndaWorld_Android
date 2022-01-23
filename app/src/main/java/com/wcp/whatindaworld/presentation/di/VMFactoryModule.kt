package com.wcp.whatindaworld.presentation.di

import android.app.Application
import com.wcp.whatindaworld.domain.usecase.FetchHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.FetchSearchedHeadlinesUseCase
import com.wcp.whatindaworld.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class VMFactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        appContext: Application,
        fetchUseCase: FetchHeadlinesUseCase,
        fetchSearchedUseCase: FetchSearchedHeadlinesUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(appContext, fetchUseCase, fetchSearchedUseCase)
    }
}