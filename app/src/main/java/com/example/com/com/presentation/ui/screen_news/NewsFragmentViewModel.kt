package com.example.com.com.presentation.ui.screen_news

import androidx.lifecycle.viewModelScope
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.domian_repositories.network.NewsRepository
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.base.BaseViewModel
import com.example.com.com.presentation.models.PresentationArticles
import com.example.com.com.presentation.utils.exception.ResourceProvider
import com.example.mynewsapp.presentation.models.PresentationNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val mapFromListArticlesDomainToUi: BaseMapper<NewsDomain, PresentationNews>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val sortBy = MutableStateFlow("popular")

    val query = MutableStateFlow("")

    private val sortAndQuery = sortBy.combine(query) { sort, q ->
        Pair(sort, q)
    }
    private val _errorFlow = MutableSharedFlow<String>(replay = 0)
    val error get() = _errorFlow.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val newsFlow = sortAndQuery.flatMapLatest {
        newsRepository.getEverything(it.second)
    }.map(mapFromListArticlesDomainToUi::map).flowOn(Dispatchers.Default).catch { t: Throwable ->
        _errorFlow.emit(resourceProvider.handleException(t))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun goDetails(news: PresentationArticles) {
        navigate(NewsFragmentDirections.actionNewsToNewsDetailsFragment(news))
    }
}