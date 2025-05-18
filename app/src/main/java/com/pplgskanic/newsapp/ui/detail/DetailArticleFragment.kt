package com.pplgskanic.newsapp.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pplgskanic.newsapp.data.Resource
import com.pplgskanic.newsapp.utils.closeLoading
import com.pplgskanic.newsapp.utils.showLoading
import com.pplgskanic.newsapp.viewmodel.ViewModelFactory
import com.pplgskanic.newsapp.utils.withDateFormat
import com.pplgskanic.newsapps.R
import com.pplgskanic.newsapps.databinding.FragmentDetailArticleBinding


class DetailArticleFragment : Fragment() {
    private var _binding: FragmentDetailArticleBinding? = null
    private val binding get() = _binding!!
    private val args: DetailArticleFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setObserver()  // Jangan lupa memanggil setObserver()
    }

    private fun setObserver() {
        viewModel.getDetailArticle(args.slug).observe(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Loading -> {
                    showLoading(requireContext())

                }
                is Resource.Success -> {
                    closeLoading()
                    val article = resources.data
                    binding.imageArticle.load(article.image) {
                        transformations(RoundedCornersTransformation(12f))
                    }
                    binding.tvTitle.text = article.title
                    binding.tvCategory.text = article.category.name
                    binding.tvAuthor.text = article.user.name
                    binding.tvDate.text = article.createdAt.withDateFormat()
                    binding.tvView.text = article.viewsCount
                    binding.wvContent.text =
                        Html.fromHtml(article.content, Html.FROM_HTML_MODE_LEGACY)
                }
                is Resource.Error -> {
                    closeLoading()
                }
            }

        }

    }

    // Helper function untuk menangani perbedaan API level
    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(html)
        }
    }

    private fun setUi() = with(binding) {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            inflateMenu(R.menu.menu_article)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.bookmark -> {
                        true
                    }
                    R.id.share -> {
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}