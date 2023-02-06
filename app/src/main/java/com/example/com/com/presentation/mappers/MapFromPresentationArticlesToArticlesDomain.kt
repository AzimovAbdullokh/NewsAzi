package com.example.com.com.presentation.mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.models.PresentationArticles

class MapFromPresentationArticlesToArticlesDomain :
    BaseMapper<PresentationArticles, ArticlesDomain> {
    override fun map(from: PresentationArticles) = from.run {
        ArticlesDomain(author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content)
    }
}