package com.pplgskanic.newsapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pplgskanic.newsapp.data.datasource.CategoriesPagingSource
import com.pplgskanic.newsapp.data.remote.model.Category
import com.pplgskanic.newsapp.data.remote.model.Response
import com.pplgskanic.newsapp.data.remote.model.Sliders
import kotlinx.coroutines.flow.Flow

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

    // config paging
    private val pagingConfig = PagingConfig(
        pageSize = 5
    )

    fun getCategory(): Flow<PagingData<Category>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                CategoriesPagingSource(apiService)
            }

        ).flow
    }

}