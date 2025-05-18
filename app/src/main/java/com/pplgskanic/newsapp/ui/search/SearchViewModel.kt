package com.pplgskanic.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pplgskanic.newsapp.data.local.entity.ArticleEntity
import com.pplgskanic.newsapp.data.remote.Repository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    fun searchArticle(search: String) = repository.searchSearchArticle(search).cachedIn(viewModelScope)

    fun setBookmark(article: ArticleEntity) {
        viewModelScope.launch {
            val isBookmark = article.isBookmark
            repository.setArticleBookmark(article, !isBookmark)
        }
    }
}