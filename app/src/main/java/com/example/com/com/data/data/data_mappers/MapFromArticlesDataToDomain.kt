package com.example.com.com.data.data.data_mappers

import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData

class MapFromArticlesDataToDomain : BaseMapper<ArticlesData, ArticlesDomain> {
    override fun map(from: ArticlesData) = from.run {
        ArticlesDomain(
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
        )
    }
}