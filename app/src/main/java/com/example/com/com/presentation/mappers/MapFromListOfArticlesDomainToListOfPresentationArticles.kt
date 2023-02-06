package com.example.com.com.presentation.mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.models.PresentationArticles

class MapFromListOfArticlesDomainToListOfPresentationArticles(
    private val mapper: BaseMapper<ArticlesDomain, PresentationArticles>
): BaseMapper<List<ArticlesDomain>, List<PresentationArticles>> {
    override fun map(from: List<ArticlesDomain>) = from.run {
        this.map {
            mapper.map(it)
        }
    }
}