package com.pplgskanic.newsapp.di

import android.content.Context
import com.pplgskanic.newsapp.data.local.NewsDatabase
import com.pplgskanic.newsapp.data.remote.Repository
import com.pplgskanic.newsapp.data.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getDatabase(context)
        return Repository.getInstance(apiService, database)
    }
}