package com.pplgskanic.newsapp.data.remote

import com.pplgskanic.newsapp.data.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("public/sliders")
    suspend fun getSlider(): Response<List<Sliders>>

    @GET("public/categories")
    suspend fun getCategory(
        @Query("page") page: Int
    ): Response<Paging<Category>>

    @GET("public/posts")
    suspend fun getArticle(
        @Query("page") page: Int
    ): Response<Paging<Article>>

}