package com.example.com.com.data.cloud.cloud_mappers

import com.example.com.com.data.cloud.cloud_models.ArticlesCloud
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData

class MapFromListArticlesCloudToData(
    private val mapFromArticlesCloudToData: BaseMapper<ArticlesCloud, ArticlesData>,
) : BaseMapper<List<ArticlesCloud>, List<ArticlesData>> {
    override fun map(from: List<ArticlesCloud>) = from.map {
        mapFromArticlesCloudToData.map(it)
    }
}