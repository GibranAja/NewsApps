package com.pplgskanic.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pplgskanic.newsapp.viewmodel.ViewModelFactory
import com.pplgskanic.newsapps.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // initial viewModel dengan ktx viewmodels
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance()
    }

    // inition sliderAdapter dengan Lazy
    private val sliderAdapter by lazy {
        SliderAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setObserver()

    }
    // untuk mengambil data dari viewmodel
    private fun setObserver() {
        viewModel.slider.observe(viewLifecycleOwner) {
            sliderAdapter.submitList(it)
        }
    }

    // menampilkan pertama ui
    private fun setUi() = with(binding) {
        vpBanner.adapter = sliderAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}