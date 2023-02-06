package com.example.com.com.domain.domian_repositories.network

import com.example.com.com.domain.domian_models.NewsDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository {


    fun getEverything(
        query: String,
    ): Flow<NewsDomain>


    fun getTopHeadlinesNews(category: String, query: String): Flow<NewsDomain>


}