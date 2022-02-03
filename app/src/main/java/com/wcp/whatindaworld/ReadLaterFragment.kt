package com.wcp.whatindaworld

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcp.whatindaworld.databinding.FragmentReadLaterBinding
import com.wcp.whatindaworld.presentation.adapter.HeadlinesAdapter
import com.wcp.whatindaworld.presentation.viewmodel.NewsViewModel

class ReadLaterFragment : Fragment() {

    private lateinit var mReadLaterFragmentBinding: FragmentReadLaterBinding
    private lateinit var mViewModel: NewsViewModel
    private lateinit var mSavedHeadlinesAdapter: HeadlinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_later, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mReadLaterFragmentBinding = FragmentReadLaterBinding.bind(view)
        mViewModel = (activity as HomeActivity).mViewModel
        mSavedHeadlinesAdapter = (activity as HomeActivity).mHeadlinesAdapter
        setUpListeners()
        setupRecyclerView()
        mViewModel.fetchAllFromReadLater().observe(viewLifecycleOwner, {
            mSavedHeadlinesAdapter.mDiffer.submitList(it)
        })
    }

    fun setUpListeners() {
        mSavedHeadlinesAdapter.setItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("clicked_article", it)
            }
            findNavController().navigate(
                R.id.action_readLaterFragment_to_detailFragment,
                bundle
            )
        }
    }

    fun setupRecyclerView() {
        mReadLaterFragmentBinding.savedRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mSavedHeadlinesAdapter
        }
    }

}