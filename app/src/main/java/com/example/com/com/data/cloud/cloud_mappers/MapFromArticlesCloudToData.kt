package com.example.com.com.data.cloud.cloud_mappers

import com.example.com.com.data.cloud.cloud_models.ArticlesCloud
import com.example.com.com.domain.mapper.BaseMapper
import com.example.data.data.data_models.ArticlesData

class MapFromArticlesCloudToData : BaseMapper<ArticlesCloud, ArticlesData> {
    override fun map(from: ArticlesCloud) = from.run {
        ArticlesData(
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