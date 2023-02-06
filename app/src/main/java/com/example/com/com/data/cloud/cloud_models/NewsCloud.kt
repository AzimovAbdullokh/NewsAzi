package com.example.com.com.data.cloud.cloud_models

import com.google.gson.annotations.SerializedName

data class NewsCloud(
    @SerializedName("articles") val articles: List<ArticlesCloud>,
)