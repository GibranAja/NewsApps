package com.pplgskanic.newsapp.data.remote

import com.pplgskanic.newsapp.data.remote.model.Response
import com.pplgskanic.newsapp.data.remote.model.Sliders

class Repository private constructor(
    private val apiService: ApiService
) {

    companion object {
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository = instance ?: synchronized(this)
        {
            instance ?: Repository(apiService)
        }
    }

    suspend fun getSlider(): Response<List<Sliders>> = apiService.getSlider()


}