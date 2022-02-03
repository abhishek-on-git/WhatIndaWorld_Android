package com.wcp.whatindaworld.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcp.whatindaworld.domain.usecase.FetchFromReadLaterUseCase
import com.wcp.whatindaworld.domain.usecase.FetchHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.FetchSearchedHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.SaveToReadLaterUseCase

class NewsViewModelFactory(
    private val appContext: Application,
    private val fetchUseCase: FetchHeadlinesUseCase,
    private val fetchSearchedUseCase: FetchSearchedHeadlinesUseCase,
    private val saveToReadLaterUseCase: SaveToReadLaterUseCase,
    private val fetchFromReadLaterUseCase: FetchFromReadLaterUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            appContext,
            fetchUseCase,
            fetchSearchedUseCase,
            saveToReadLaterUseCase,
            fetchFromReadLaterUseCase
        ) as T
    }

}