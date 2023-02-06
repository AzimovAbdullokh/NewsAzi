package com.example.com.com.presentation.di

import android.content.Context
import com.example.com.com.domain.helper.DispatchersProvider
import com.example.com.com.presentation.utils.exception.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.coroutineContext


@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

//    @Provides
//    fun provideResponseHandlerImpl(
//        dispatchersProvider: DispatchersProvider,
//    ): ResponseHandler = ResponseHandlerImpl(dispatchersProvider = dispatchersProvider)

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProvider.Base()

    @Provides

    fun provideHandlerException(
    ): ResourceProvider = ResourceProvider.Base()
}