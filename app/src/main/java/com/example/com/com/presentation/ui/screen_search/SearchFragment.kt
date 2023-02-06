package com.example.com.com.presentation.ui.screen_search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.com.com.presentation.adapter.NewsAdapter
import com.example.com.com.presentation.adapter.NewsAdapter.Companion.HORIZONTAL_TYPE
import com.example.com.com.presentation.base.BaseFragment
import com.example.com.com.presentation.models.PresentationArticles
import com.example.com.com.presentation.utils.extension.showView
import com.example.newsazi.R
import com.example.newsazi.databinding.FragmentSearchBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(FragmentSearchBinding::inflate),
    NewsAdapter.RecyclerOnClickListener {

    override val viewModel: SearchFragmentViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).showView()
    }

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(this, HORIZONTAL_TYPE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeView()
        searchNews()
        observeViewModel()
    }


    private fun observeView() = with(requireBinding()) {
        searchNewsRv.adapter = newsAdapter
    }

    private fun searchNews() {
        query.doAfterTextChanged { text ->
            viewModel.updateKeyword(text?.toString() ?: "")
        }
        viewModel.query.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach(::updateSearchQuery).launchIn(lifecycleScope)
    }

    private fun observeViewModel() = with(viewModel) {
        error.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        lifecycleScope.launchWhenStarted {
            allNewsSharedFlow.collectLatest {
                newsAdapter.articlesList = it.articles
                requireBinding().shimmerLayout.visibility = View.INVISIBLE
                requireBinding().searchConst.visibility = View.VISIBLE
            }
        }
    }

    private fun updateSearchQuery(searchQuery: String) {
        with(requireBinding().query) {
            if ((text?.toString() ?: "") != searchQuery) {
                viewModel.updateKeyword(searchQuery)
                setText(searchQuery)
            }
        }
    }

    override fun onItemClick(news: PresentationArticles) {
        viewModel.goDetails(news)
    }

    override fun onLongSaveClick(news: PresentationArticles) {
        viewModel.goDetails(news)
    }

    override fun showNewsToUrl(url: String) {}
}