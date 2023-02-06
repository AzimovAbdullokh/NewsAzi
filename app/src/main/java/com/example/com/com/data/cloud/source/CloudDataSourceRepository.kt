package com.example.com.com.data.cloud.source

import com.example.com.com.data.data.data_models.NewsData
import kotlinx.coroutines.flow.Flow

interface CloudDataSourceRepository {
    fun getEverythingNews(query: String): Flow<NewsData>
    fun getTopHeadlinesNews(category: String, query: String): Flow<NewsData>
}