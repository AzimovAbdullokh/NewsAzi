package com.example.com.com.presentation.ui.screen_search

import androidx.lifecycle.viewModelScope
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.domian_repositories.network.NewsRepository
import com.example.com.com.domain.helper.DispatchersProvider
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.base.BaseViewModel
import com.example.com.com.presentation.models.PresentationArticles
import com.example.com.com.presentation.utils.exception.ResourceProvider
import com.example.mynewsapp.presentation.models.PresentationNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val mapFromListArticlesDomainToUi: BaseMapper<NewsDomain, PresentationNews>,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _errorFlow = MutableSharedFlow<String>(replay = 0)
    val error get() = _errorFlow.asSharedFlow()

    private val sortByFlow = MutableStateFlow("relevancy")

    private val sortByAndKeywordFlow = sortByFlow.combine(_query) { sortBy, keyword ->
        Pair(sortBy, keyword)
    }

    val allNewsSharedFlow = sortByAndKeywordFlow.flatMapLatest { sortTypes ->
        repository.getEverything(query = sortTypes.second)
    }.map(mapFromListArticlesDomainToUi::map).flowOn(dispatchersProvider.default())
        .catch { error: Throwable ->
            _errorFlow.emit(resourceProvider.handleException(error))
        }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun updateKeyword(keyword: String) = _query.tryEmit(keyword)

    fun goDetails(news: PresentationArticles) {
        navigate(SearchFragmentDirections.actionSearchToNewsDetailsFragment(news))
    }

}