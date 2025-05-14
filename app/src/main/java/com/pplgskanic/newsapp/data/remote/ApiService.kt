package com.pplgskanic.newsapp.data.remote

import com.pplgskanic.newsapp.data.remote.model.Response
import com.pplgskanic.newsapp.data.remote.model.Sliders
import retrofit2.http.GET

interface ApiService {

    @GET("public/sliders")
    suspend fun getSlider(): Response<List<Sliders>>

}