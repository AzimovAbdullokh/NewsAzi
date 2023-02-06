package com.example.com.com.presentation.di

import com.example.com.com.data.cloud.cloud_mappers.MapFromArticlesCloudToData
import com.example.com.com.data.cloud.cloud_mappers.MapFromListArticlesCloudToData
import com.example.com.com.data.cloud.cloud_mappers.MapFromNewsCloudToData
import com.example.com.com.data.cloud.cloud_models.ArticlesCloud
import com.example.com.com.data.cloud.cloud_models.NewsCloud
import com.example.com.com.data.data.data_mappers.MapFromArticlesDataToDomain
import com.example.com.com.data.data.data_mappers.MapFromListArticlesDataToDomain
import com.example.com.com.data.data.data_mappers.MapFromNewsDataToDomain
import com.example.com.com.domain.domian_models.ArticlesDomain
import com.example.com.com.domain.domian_models.NewsDomain
import com.example.com.com.domain.mapper.BaseMapper
import com.example.com.com.presentation.mappers.MapFromArticlesDomainToPresentationArticles
import com.example.com.com.presentation.mappers.MapFromListOfArticlesDomainToListOfPresentationArticles
import com.example.com.com.presentation.mappers.MapFromNewsDomainToPresentationNews
import com.example.com.com.presentation.mappers.MapFromPresentationArticlesToArticlesDomain
import com.example.data.data.data_models.ArticlesData
import com.example.com.com.data.data.data_models.NewsData
import com.example.com.com.presentation.models.PresentationArticles
import com.example.mynewsapp.presentation.models.PresentationNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    //   Mappers From CLoud To Data
    @Provides
    @Singleton
    fun provideMapperFromArticlesCloudToData(): BaseMapper<ArticlesCloud, ArticlesData> =
        MapFromArticlesCloudToData()

    @Provides
    @Singleton
    fun provideMapperFromListArticlesCloudToData(
        mapFromArticlesCloudToData: BaseMapper<ArticlesCloud, ArticlesData>,
    ): BaseMapper<List<ArticlesCloud>, List<ArticlesData>> =
        MapFromListArticlesCloudToData(mapFromArticlesCloudToData = mapFromArticlesCloudToData)

    @Provides
    @Singleton
    fun provideMapperFromNewsCloudToData(
        mapFromListArticlesCloudToData: BaseMapper<List<ArticlesCloud>, List<ArticlesData>>,
    ): BaseMapper<NewsCloud, NewsData> =
        MapFromNewsCloudToData(mapFromListArticlesCloudToData = mapFromListArticlesCloudToData)

    // Mappers From Data To Domain

    @Provides
    @Singleton
    fun provideMapperFromArticlesDataToDomain(): BaseMapper<ArticlesData, ArticlesDomain> =
        MapFromArticlesDataToDomain()

    @Provides
    @Singleton
    fun provideMapperFromListArticlesDataToDomain(
        mapFromArticlesDataToDomain: BaseMapper<ArticlesData, ArticlesDomain>,
    ): BaseMapper<List<ArticlesData>, List<ArticlesDomain>> =
        MapFromListArticlesDataToDomain(mapFromArticlesDataToDomain = mapFromArticlesDataToDomain)

    @Provides
    @Singleton
    fun provideMapperFromNewsDataToDomain(
        mapFromListArticlesDataToDomain: BaseMapper<List<ArticlesData>, List<ArticlesDomain>>,
    ): BaseMapper<NewsData, NewsDomain> =
        MapFromNewsDataToDomain(mapFromListArticlesDataToDomain = mapFromListArticlesDataToDomain)

    // Mapper From Domain To Ui

    @Provides
    @Singleton
    fun provideMapperFromArticlesDomainToUi(): BaseMapper<ArticlesDomain, PresentationArticles> =
        MapFromArticlesDomainToPresentationArticles()

    @Provides
    @Singleton
    fun provideMapperFromListOfArticlesDomainToUi(
        mapper: BaseMapper<ArticlesDomain, PresentationArticles>,
    ): BaseMapper<List<ArticlesDomain>, List<PresentationArticles>> =
        MapFromListOfArticlesDomainToListOfPresentationArticles(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapperFromNewsDomainToPresentationNews(
        mapper: BaseMapper<List<ArticlesDomain>, List<PresentationArticles>>,
    ): BaseMapper<NewsDomain, PresentationNews> =
        MapFromNewsDomainToPresentationNews(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromPresentationArticlesToArticlesDomain(): BaseMapper<PresentationArticles, ArticlesDomain> =
        MapFromPresentationArticlesToArticlesDomain()

}