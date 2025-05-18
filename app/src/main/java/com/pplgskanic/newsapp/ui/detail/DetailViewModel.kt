package com.pplgskanic.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import com.pplgskanic.newsapp.data.remote.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailArticle(slug: String) = repository.getDetailArticle(slug)
}