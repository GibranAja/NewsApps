package com.pplgskanic.newsapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pplgskanic.newsapp.ui.bookmark.BookmarkViewModel
import com.pplgskanic.newsapp.ui.categories.CategoryViewModel
import com.pplgskanic.newsapp.ui.detail.DetailViewModel
import com.pplgskanic.newsapp.ui.home.HomeViewModel
import com.pplgskanic.newsapp.data.remote.Repository
import com.pplgskanic.newsapp.di.Injection
import com.pplgskanic.newsapp.ui.search.SearchViewModel
import com.pplgskanic.newsapp.utils.SettingPreferences

class ViewModelFactory(
    private val repository: Repository,
    private val settingPreferences: SettingPreferences
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepository(context),
                Injection.providePrefDataStore(context)
            )
        }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository, settingPreferences) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}