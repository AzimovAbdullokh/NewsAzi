package com.example.com.com.presentation.ui.screen_news_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.com.com.presentation.base.BaseFragment
import com.example.com.com.presentation.models.PresentationArticles
import com.example.com.com.presentation.utils.extension.hideView
import com.example.newsazi.R
import com.example.newsazi.databinding.FragmentNewsDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso


class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding, NewsDetailsFragmentViewModel>(
    FragmentNewsDetailsBinding::inflate) {

    override val viewModel: NewsDetailsFragmentViewModel by viewModels()

    private val newss: PresentationArticles by lazy(LazyThreadSafetyMode.NONE) {
        NewsDetailsFragmentArgs.fromBundle(requireArguments()).news
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).hideView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNewsUi(newss)
        observeClickers()

    }

    private fun observeClickers() = with(requireBinding()) {
        backImgButton.setOnClickListener {
            viewModel.goBack()
        }
        goWebBtn.setOnClickListener {
            showNewsToUrl(newss.url.toString())
        }

    }

    private fun setNewsUi(news: PresentationArticles) = with(requireBinding()) {
        Picasso.get().load(news.urlToImage).into(newsImage)
        newsTitle.text = news.title
        newsDescription.text = news.description
        publishedAt.text = news.publishedAt

    }

    override fun showNewsToUrl(url: String) {
        activity?.intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(activity?.intent)
    }


}