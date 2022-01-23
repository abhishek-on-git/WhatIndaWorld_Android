package com.wcp.whatindaworld.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wcp.whatindaworld.data.model.Article
import com.wcp.whatindaworld.databinding.HeadlineListItemBinding

class HeadlinesAdapter: RecyclerView.Adapter<HeadlinesAdapter.HeadlinesViewHolder>() {

    private val mCallback = object: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val mDiffer = AsyncListDiffer(this, mCallback)

    private var itemClickListener: ((Article) -> Unit)? = null

    fun setItemClickListener(listener: (Article) -> Unit) {
        itemClickListener = listener
    }

    fun updateList(list: List<Article>) {
        mDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        val binding = HeadlineListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeadlinesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        holder.bind(mDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    inner class HeadlinesViewHolder(
        private val binding: HeadlineListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.title.text = article.title
            binding.summary.text = article.description
            binding.source.text = article.source.name
            binding.datePublished.text = article.publishedAt
            Glide.with(binding.thumbnail.context)
                .load(article.urlToImage)
                .into(binding.thumbnail)

            binding.root.setOnClickListener{
                itemClickListener?.let {
                    it(article)
                }
            }
        }
    }
}