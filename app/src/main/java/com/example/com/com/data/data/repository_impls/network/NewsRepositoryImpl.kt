package com.example.com.com.data.data.repository_impls.network

import com.example.com.com.data.cloud.source.CloudDataSourceRepository
import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.domian_repositories.network.NewsRepository
import com.example.com.com.domain.helper.DispatchersProvider
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData
import com.example.com.com.data.data.data_models.NewsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val cloudDataSourceNews: CloudDataSourceRepository,
    private val mapFromNewsDataToDomain: BaseMapper<NewsData, NewsDomain>,
    private val mapFromListArticlesDataToDomain: BaseMapper<List<ArticlesData>, List<ArticlesDomain>>,
    private val dispatchersProvider: DispatchersProvider,
) : NewsRepository {

    override fun getEverything(
        query: String,
    ): Flow<NewsDomain> = cloudDataSourceNews.getEverythingNews(query)
        .map(mapFromNewsDataToDomain::map).flowOn(Dispatchers.Default)

    override fun getTopHeadlinesNews(category: String, query: String): Flow<NewsDomain> =
        cloudDataSourceNews.getTopHeadlinesNews(category = category, query = query)
            .map(mapFromNewsDataToDomain::map).flowOn(dispatchersProvider.default())
}