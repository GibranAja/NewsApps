package com.pplgskanic.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pplgskanic.newsapps.databinding.FragmentHomeBinding
import com.pplgskanic.newsapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// Deklarasi kelas HomeFragment sebagai subclass dari Fragment
class HomeFragment : Fragment() {

    // Deklarasi variabel binding sebagai nullable FragmentHomeBinding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Inisialisasi viewModel dengan viewModels
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance()
    }

    // Inisialisasi sliderAdapter dengan Lazy
    private val sliderAdapter by lazy {
        SliderAdapter()
    }

    // Inisialisasi categoryAdapter dengan Lazy
    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    // Method onCreateView dipanggil ketika Fragment pertama kali dibuat
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout FragmentHomeBinding dan set ke _binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Return root dari _binding
        return binding.root
    }

    // Method onViewCreated dipanggil setelah Fragment dibuat dan tampilan dibuat
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Panggil method setUi dan setObserver
        setUi()
        setObserver()
    }

    // Method setObserver untuk mengambil data dari viewModel
    private fun setObserver() {
        // Memantau LiveData slider dari viewModel dan mengirimkan data ke sliderAdapter
        viewModel.slider.observe(viewLifecycleOwner) {
            sliderAdapter.submitList(it)
        }

        // Memantau Flow category dari viewModel dan mengirimkan data ke categoryAdapter dengan collectLatest
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.category.collectLatest {
                categoryAdapter.submitData(it)
            }
        }
    }

    // Method setUi untuk menampilkan tampilan pertama kali
    private fun setUi() = with(binding) {
        // Set adapter sliderAdapter ke ViewPager vpBanner
        vpBanner.adapter = sliderAdapter
        // Set adapter categoryAdapter ke RecyclerView rvCategory, dengan layout manager horizontal
        rvCategory.apply {
            adapter = categoryAdapter
            setHasFixedSize(false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    // Method onDestroyView dipanggil ketika tampilan Fragment dihancurkan
    override fun onDestroyView() {
        super.onDestroyView()
        // Set _binding menjadi null
        _binding = null
    }
}