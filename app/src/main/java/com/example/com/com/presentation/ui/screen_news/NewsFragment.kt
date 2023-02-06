package com.example.com.com.presentation.ui.screen_news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.com.com.presentation.adapter.NewsAdapter
import com.example.com.com.presentation.adapter.NewsAdapter.Companion.PORTRAIT_TYPE
import com.example.com.com.presentation.base.BaseFragment
import com.example.com.com.presentation.models.PresentationArticles
import com.example.com.com.presentation.utils.extension.showView
import com.example.newsazi.R
import com.example.newsazi.databinding.FragmentNewsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NewsFragment :
    BaseFragment<FragmentNewsBinding, NewsFragmentViewModel>(FragmentNewsBinding::inflate),
    NewsAdapter.RecyclerOnClickListener {

    override val viewModel: NewsFragmentViewModel by viewModels()

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(this, PORTRAIT_TYPE)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observe()
    }

    private fun setupViews() = with(requireBinding()) {
        newsRv.adapter = newsAdapter
    }

    private fun observe() = with(requireBinding()) {
        lifecycleScope.launchWhenStarted {
            viewModel.newsFlow.collect {
                newsAdapter.articlesList = it.articles
                shimmerLayout.stopShimmer()
                shimmerLayout.visibility = View.INVISIBLE
                newsConst.visibility = View.VISIBLE
            }
        }
        viewModel.query.onEach {

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