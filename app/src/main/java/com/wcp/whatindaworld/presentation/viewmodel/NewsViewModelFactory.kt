package com.wcp.whatindaworld.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcp.whatindaworld.domain.usecase.FetchHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.FetchSearchedHeadlinesUseCase

class NewsViewModelFactory(
    private val appContext: Application,
    private val fetchUseCase: FetchHeadlinesUseCase,
    private val fetchSearchedUseCase: FetchSearchedHeadlinesUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            appContext,
            fetchUseCase,
            fetchSearchedUseCase
        ) as T
    }

}