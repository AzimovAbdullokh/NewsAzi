package com.example.com.com.presentation.di

import com.example.com.com.data.cloud.api.news.NewsApi
import com.example.com.com.data.cloud.cloud_models.ArticlesCloud
import com.example.com.com.data.cloud.cloud_models.NewsCloud
import com.example.com.com.data.cloud.source.CloudDataSourceRepositoryImpl
import com.example.com.com.data.data.repository_impls.network.NewsRepositoryImpl
import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.domian_repositories.network.NewsRepository
import com.example.com.com.domain.helper.DispatchersProvider
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.data.cloud.source.CloudDataSourceRepository
import com.example.data.data.data_models.ArticlesData
import com.example.com.com.data.data.data_models.NewsData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        dataSource: CloudDataSourceRepository,
        mapper: BaseMapper<NewsData, NewsDomain>,
        dispatchersProvider: DispatchersProvider,
        mapFromListArticlesDataToDomain: BaseMapper<List<ArticlesData>, List<ArticlesDomain>>,
    ): NewsRepository = NewsRepositoryImpl(cloudDataSourceNews = dataSource,
        mapFromNewsDataToDomain = mapper,
        dispatchersProvider = dispatchersProvider,
        mapFromListArticlesDataToDomain = mapFromListArticlesDataToDomain)


//    @Provides
//    @Singleton
//    fun provideSaveNewsRepository(
//        dataSource: CloudDataSourceRepository,
//        articleDataToDomainMapper: BaseMapper<List<ArticlesData>, List<ArticlesDomain>>,
//        articleDomainToDataMapper: BaseMapper<ArticlesDomain, ArticlesData>,
//    ): SaveNewsRepository = SaveNewsRepositoryImpl(dataSource = dataSource,
//        articleDomainToDataMapper = articleDomainToDataMapper,
//        articleDataToDomainMapper = articleDataToDomainMapper)


    @Provides
    @Singleton
    fun provideCloudDataSource(
        api: NewsApi,
        mapper: BaseMapper<NewsCloud, NewsData>,
        dispatchersProvider: DispatchersProvider,
    ): CloudDataSourceRepository = CloudDataSourceRepositoryImpl(newsApi = api,
        mapFromNewsCloudToNewsData = mapper,
        dispatchersProvider = dispatchersProvider,
    )


//    @Provides
//    @Singleton
//    fun provideCacheDataSource(
//        dao: ArticlesDao,
//        mapper: BaseMapper<ArticlesData, NewsStorage>,
//        mapList: BaseMapper<List<NewsStorage>, List<ArticlesData>>,
//    ): CacheDataSource = CacheDataSourceImpl(dao = dao, mapper = mapper, mapList = mapList)

}
