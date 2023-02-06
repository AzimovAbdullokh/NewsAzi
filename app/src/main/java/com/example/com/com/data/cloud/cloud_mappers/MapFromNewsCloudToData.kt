package com.example.com.com.data.cloud.cloud_mappers

import com.example.com.com.data.cloud.cloud_models.ArticlesCloud
import com.example.com.com.data.cloud.cloud_models.NewsCloud
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData
import com.example.com.com.data.data.data_models.NewsData

class MapFromNewsCloudToData(
    private val mapFromListArticlesCloudToData: BaseMapper<List<ArticlesCloud>, List<ArticlesData>>,
) : BaseMapper<NewsCloud, NewsData> {
    override fun map(from: NewsCloud) = from.run {
        NewsData(articles = mapFromListArticlesCloudToData.map(articles))
    }
}