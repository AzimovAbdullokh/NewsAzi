package com.example.data.data.data_models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ArticlesData(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
) : Parcelable