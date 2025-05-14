package com.pplgskanic.newsapp.di

import com.pplgskanic.newsapp.data.remote.Repository
import com.pplgskanic.newsapp.data.remote.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}