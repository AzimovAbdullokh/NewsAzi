package com.example.com.com.presentation.mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.models.PresentationArticles

class MapFromArticlesDomainToPresentationArticles :
    BaseMapper<ArticlesDomain, PresentationArticles> {
    override fun map(from: ArticlesDomain) = from.run {
        PresentationArticles(author = author, title = title, description = description, url = url, urlToImage = urlToImage, publishedAt = publishedAt, content = content)
    }
}