package com.example.com.com.presentation.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

//    @Singleton
//    @Provides
//    fun provideArticleDatabase(
//        @ApplicationContext app: Context,
//    ) = Room.databaseBuilder(app, ArticlesDatabase::class.java, "news_db.db").build()
//
//    @Singleton
//    @Provides
//    fun provideArticleDao(db: ArticlesDatabase) = db.getArticleDao()

}