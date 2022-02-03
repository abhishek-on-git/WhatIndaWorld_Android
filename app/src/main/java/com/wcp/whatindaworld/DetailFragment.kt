package com.wcp.whatindaworld

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.wcp.whatindaworld.databinding.FragmentDetailBinding
import com.wcp.whatindaworld.presentation.viewmodel.NewsViewModel

class DetailFragment : Fragment() {

    private lateinit var mFragmentDetailBinding: FragmentDetailBinding
    private lateinit var mViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentDetailBinding = FragmentDetailBinding.bind(view)
        val args: DetailFragmentArgs by navArgs()
        val article = args.clickedArticle

        mViewModel = (activity as HomeActivity).mViewModel

        mFragmentDetailBinding.webView.apply {
            webViewClient = WebViewClient()
            if(!article.url.isNullOrEmpty()) {
                loadUrl(article.url)
            }
        }

        mFragmentDetailBinding.saveArticleButton.setOnClickListener {
            mViewModel.saveToReadLater(article)
            Snackbar.make(view, "Saved to Read later.", Snackbar.LENGTH_LONG).show()
        }
    }
}