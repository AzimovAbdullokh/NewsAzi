package com.example.com.com.data.data.data_mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData

class MapFromListArticlesDataToDomain(
    private val mapFromArticlesDataToDomain: BaseMapper<ArticlesData, ArticlesDomain>,
) : BaseMapper<List<ArticlesData>, List<ArticlesDomain>> {
    override fun map(from: List<ArticlesData>) = from.map {
        mapFromArticlesDataToDomain.map(it)
    }
}