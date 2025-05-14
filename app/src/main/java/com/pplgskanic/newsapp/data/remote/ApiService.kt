package com.pplgskanic.newsapp.data.remote

import com.pplgskanic.newsapp.data.remote.model.Category
import com.pplgskanic.newsapp.data.remote.model.Paging
import com.pplgskanic.newsapp.data.remote.model.Response
import com.pplgskanic.newsapp.data.remote.model.Sliders
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("public/sliders")
    suspend fun getSlider(): Response<List<Sliders>>

    @GET("public/categories")
    suspend fun getCategory(
        @Query("page") page: Int,
    ): Response<Paging<Category>>

}