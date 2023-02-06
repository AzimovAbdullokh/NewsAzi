package com.example.com.com.domain.usecase

import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.domian_repositories.network.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueryNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke(query: String): Flow<NewsDomain> {
        return repository.getEverything(query = query)
    }
}