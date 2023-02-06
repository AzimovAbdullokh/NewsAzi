package com.example.com.com.presentation.mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.models.PresentationArticles
import com.example.mynewsapp.presentation.models.PresentationNews

class MapFromNewsDomainToPresentationNews(
    private val mapper: BaseMapper<List<ArticlesDomain>, List<PresentationArticles>>,
) : BaseMapper<NewsDomain, PresentationNews> {
    override fun map(from: NewsDomain) = from.run {
        PresentationNews(articles = mapper.map(articles))
    }
}