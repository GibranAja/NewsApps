package com.pplgskanic.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pplgskanic.newsapp.data.remote.Repository
import com.pplgskanic.newsapp.data.remote.model.Sliders
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _sliders = MutableLiveData<List<Sliders>>()
    val slider: LiveData<List<Sliders>> get() = _sliders

    init {
        getSliders()
    }

    private fun getSliders() {
        viewModelScope.launch {
            try {
                val remote = repository.getSlider().data
                _sliders.postValue(remote)
            } catch (e: Exception) {
                Log.d("TAG", "Error Slider $e")
            }
        }
    }
}