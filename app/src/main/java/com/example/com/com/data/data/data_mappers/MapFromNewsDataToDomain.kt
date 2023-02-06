package com.example.com.com.data.data.data_mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData
import com.example.com.com.data.data.data_models.NewsData

class MapFromNewsDataToDomain(
    private val mapFromListArticlesDataToDomain: BaseMapper<List<ArticlesData>, List<ArticlesDomain>>,
) : BaseMapper<NewsData, NewsDomain> {
    override fun map(from: NewsData) = from.run {
        NewsDomain(articles = mapFromListArticlesDataToDomain.map(articles))
    }
}