package com.pplgskanic.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pplgskanic.newsapp.data.local.entity.ArticleEntity
import com.pplgskanic.newsapp.data.remote.Repository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailArticle(slug: String) = repository.getDetailArticle(slug)

    fun getDetailCategory(slug: String) = repository.getDetailCategory(slug)

    fun setBookmark(articleEntity: ArticleEntity) {
        viewModelScope.launch {
            val isBookmarked = articleEntity.isBookmark
            repository.setArticleBookmark(articleEntity, !isBookmarked)
        }
    }
}