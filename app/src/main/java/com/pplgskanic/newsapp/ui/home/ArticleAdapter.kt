package com.pplgskanic.newsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pplgskanic.newsapps.R
import com.pplgskanic.newsapp.data.remote.model.Article
import com.pplgskanic.newsapp.utils.withDateFormat
import com.pplgskanic.newsapps.databinding.ItemArticleBinding

class ArticleAdapter(
) : PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articles = getItem(position)
        holder.bind(articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article?) {
            item?.let {
                with(binding) {
                    imageArticle.load(it.image) {
                        crossfade(true)
                        crossfade(400)
                        error(R.drawable.ic_broken_image)
                        transformations(RoundedCornersTransformation(25f))
                    }
                    tvTitle.text = it.title
                    tvCategory.text = it.category.name
                    tvAuthorDate.text = it.user.name + " - " + it.createdAt.withDateFormat()

                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Article,
                newItem: Article
            ): Boolean =
                oldItem == newItem
        }
    }

}