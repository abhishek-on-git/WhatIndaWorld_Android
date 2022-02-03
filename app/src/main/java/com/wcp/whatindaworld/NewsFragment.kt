package com.wcp.whatindaworld

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wcp.whatindaworld.databinding.FragmentNewsBinding
import com.wcp.whatindaworld.presentation.adapter.HeadlinesAdapter
import com.wcp.whatindaworld.presentation.viewmodel.NewsViewModel
import com.wcp.whatindaworld.util.Resource
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFragment : Fragment() {

    private lateinit var mViewModel: NewsViewModel
    private lateinit var mFragmentNewsBinding: FragmentNewsBinding
    private lateinit var mFreshHeadlinesAdapter: HeadlinesAdapter

    private val mCountry = "in"
    private var mPage = 1
    private var mPages = 0
    private var isLoading = false
    private var isLastPage = false
    private var isScrolling: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = (activity as HomeActivity).mViewModel
        mFreshHeadlinesAdapter = (activity as HomeActivity).mHeadlinesAdapter
        mFragmentNewsBinding = FragmentNewsBinding.bind(view)
        setUpListeners()
        setupRecyclerView()
        populateNewsList()
        setUpSearchedView()
    }

    fun setupRecyclerView() {
        mFragmentNewsBinding.headlinesRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mFreshHeadlinesAdapter
            addOnScrollListener(mScrollListener)
        }
    }

    fun setUpListeners() {
        mFreshHeadlinesAdapter.setItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("clicked_article", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_detailFragment,
                bundle
            )
        }
    }

    fun setUpSearchedView() {
        mFragmentNewsBinding.headlineSearchView.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    mViewModel.fetchSearchedHeadlines(mCountry, query.toString(), mPage)
                    populateSearchedNewsList()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.toString().isNullOrEmpty()){
                        populateNewsList()
                        Log.i("Abhishek", "1$newText"+"empty")
                        return false
                    } else if(newText.toString().length >= 3) {
                        MainScope().launch {
                            Log.i("Abhishek", "query : $newText")
                            delay(1000L)
                            mViewModel.fetchSearchedHeadlines(mCountry, newText.toString(), mPage)
                            populateSearchedNewsList()
                        }
                    }
                    return false
                }

            }
        )

        mFragmentNewsBinding.headlineSearchView.setOnCloseListener {
            setupRecyclerView()
            populateNewsList()
            false
        }
    }

    private fun populateNewsList() {
        mViewModel.fetchHeadlines(mCountry, mPage)
        mViewModel.fetchHeadlinesAsLiveData().observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        mFreshHeadlinesAdapter.updateList(it.articles)
                        Log.i("Abhishek", "total : ${it.totalResults}")
                        if(it.totalResults % 20 == 0){
                            mPages = it.totalResults / 20
                        } else {
                            mPages = it.totalResults / 20 + 1
                        }
                        isLastPage = mPages == mPage
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error fetching Headlines: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    fun populateSearchedNewsList(){
        mViewModel.fetchSearchedHeadlinesAsLiveData().observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        mFreshHeadlinesAdapter.updateList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error fetching Headlines: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private val mScrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = mFragmentNewsBinding.headlinesRV.layoutManager as LinearLayoutManager
            val itemCountInCurrentPage = layoutManager.itemCount
            val visibleItemCount = layoutManager.childCount
            val firstItemPosition = layoutManager.findFirstVisibleItemPosition()

            val pageEndReached = firstItemPosition + visibleItemCount >= itemCountInCurrentPage
            val shouldPaginate = !isLastPage && !isLoading && pageEndReached && isScrolling

            if(shouldPaginate){
                mPage++
                Log.i("Abhishek", "Fetching headlines again")
                mViewModel.fetchHeadlines(mCountry, mPage)
                isScrolling = false
            }
        }
    }

    private fun showProgressBar(){
        isLoading = true
        mFragmentNewsBinding.headlinesProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        mFragmentNewsBinding.headlinesProgressBar.visibility = View.GONE
    }
}