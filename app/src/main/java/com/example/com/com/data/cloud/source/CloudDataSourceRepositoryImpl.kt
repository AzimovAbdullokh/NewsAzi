package com.example.com.com.data.cloud.source

import com.example.com.com.data.cloud.api.news.NewsApi
import com.example.com.com.data.cloud.cloud_models.NewsCloud
import com.example.com.com.data.data.data_models.NewsData
import com.example.com.com.domain.helper.DispatchersProvider
import com.example.com.com.domain.mapper.BaseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CloudDataSourceRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromNewsCloudToNewsData: BaseMapper<NewsCloud, NewsData>,
//    private val mapFromListArticlesCloudToData: BaseMapper<List<ArticlesCloud>, List<ArticlesData>>,
) : CloudDataSourceRepository {


    override fun getEverythingNews(
        query: String,
    ): Flow<NewsData> = flow {
        emit(newsApi.getAllNews(keyword = query))
    }.flowOn(Dispatchers.IO).map {
        it.body()!!
    }.map(mapFromNewsCloudToNewsData::map).flowOn(Dispatchers.IO)


    override fun getTopHeadlinesNews(category: String, query: String): Flow<NewsData> = flow {
        emit(newsApi.getTopHeadlines(category = category, keyword = query))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromNewsCloudToNewsData::map).flowOn(dispatchersProvider.default())
}