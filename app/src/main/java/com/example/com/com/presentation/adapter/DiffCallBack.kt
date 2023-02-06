package com.example.com.com.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.com.com.presentation.models.PresentationArticles

class DiffCallBack(
    private val oldList: List<PresentationArticles>,
    private val newList: List<PresentationArticles>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].url == newList[newItemPosition].url

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}