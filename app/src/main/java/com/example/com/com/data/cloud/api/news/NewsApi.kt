package com.example.com.com.data.cloud.api.news

import com.example.com.com.data.cloud.api.utils.Utils
import com.example.com.com.data.cloud.api.utils.Utils.EVERYTHING
import com.example.com.com.data.cloud.api.utils.Utils.TOP_HEADLINES
import com.example.com.com.data.cloud.cloud_models.ArticlesCloud
import com.example.com.com.data.cloud.cloud_models.NewsCloud
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(EVERYTHING)
    suspend fun getAllNews(
        @Query("q") keyword: String,
        @Query("domains") domains: String? = "bbc.com , euronews.com , edition.cnn.com , " + "news.google.com , aljazeera.com",
        @Query("sortBy") sortBy: String = "relevancy",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String? = Utils.API_KEY,
        @Query("page") @androidx.annotation.IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @androidx.annotation.IntRange(from = 1,
            to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Response<NewsCloud>

    @GET(TOP_HEADLINES)
    suspend fun getTopHeadlines(
        @Query("q") keyword: String,
        @Query("country") country: String = "de",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Utils.API_KEY,
    ): Response<NewsCloud>
}

private const val HEADER_API_KEY = "X-Api-Key"

const val DEFAULT_PAGE_SIZE = 20
const val MAX_PAGE_SIZE = 20

fun NewsService(apiKey: String): NewsApi {
    val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val authorizedRequest =
            chain.request().newBuilder().addHeader(HEADER_API_KEY, apiKey).build()
        chain.proceed(authorizedRequest)
    }.build()


    val retrofit = Retrofit.Builder().baseUrl(Utils.BASE_URL).client(okHttpClient).build()

    return retrofit.create(NewsApi::class.java)
}