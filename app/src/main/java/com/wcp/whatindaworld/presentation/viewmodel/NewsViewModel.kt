package com.wcp.whatindaworld.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wcp.whatindaworld.data.model.APIResponse
import com.wcp.whatindaworld.domain.usecase.FetchHeadlinesUseCase
import com.wcp.whatindaworld.domain.usecase.FetchSearchedHeadlinesUseCase
import com.wcp.whatindaworld.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val appContext: Application,
    private val fetchUseCase: FetchHeadlinesUseCase,
    private val fetchSearchedUseCase: FetchSearchedHeadlinesUseCase
): AndroidViewModel(appContext) {

    private val mHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    private val mSearchedHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun fetchHeadlinesAsLiveData(): LiveData<Resource<APIResponse>> {
        return mHeadlines
    }

    fun fetchSearchedHeadlinesAsLiveData(): LiveData<Resource<APIResponse>> {
        return mSearchedHeadlines
    }

    fun fetchHeadlines(
        country: String,
        page: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if(isConnectedToNet(appContext)) {
                mHeadlines.postValue(Resource.Loading())
                val apiResponse = fetchUseCase.execute(country, page)
                mHeadlines.postValue(apiResponse)
            } else {
                mHeadlines.postValue(Resource.Error("You're offline!"))
            }
        } catch (e: Exception) {
            mHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun fetchSearchedHeadlines(
        country: String,
        searchQuery: String,
        page: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if(isConnectedToNet(appContext)) {
                mSearchedHeadlines.postValue(Resource.Loading())
                val apiResponse = fetchSearchedUseCase.execute(country, searchQuery, page)
                mSearchedHeadlines.postValue(apiResponse)
            } else {
                mSearchedHeadlines.postValue(Resource.Error("You're offline!"))
            }
        } catch (e: Exception) {
            mSearchedHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun isConnectedToNet(context: Context?): Boolean {
        if(context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if(activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }


}