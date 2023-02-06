package com.example.com.com.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.com.com.presentation.models.PresentationArticles
import com.example.newsazi.R
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val listener: RecyclerOnClickListener,
    private val objectViewType: Int,
) : RecyclerView.Adapter<NewsViewHolder>() {

    var articlesList = listOf<PresentationArticles>()
        set(value) {
            val callback = DiffCallBack(articlesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout = when (viewType) {
            PORTRAIT_TYPE -> R.layout.item_row_news
            HORIZONTAL_TYPE -> R.layout.item_for_search
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articlesList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(articlesList[position])
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongSaveClick(articlesList[position])
            true
        }
        holder.itemNews.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.item_anim))

//        holder.search_card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,
//            R.anim.item_anim))
    }

    interface RecyclerOnClickListener {
        fun onItemClick(movie: PresentationArticles)
        fun onLongSaveClick(movie: PresentationArticles)
    }

    override fun getItemCount(): Int = articlesList.size


    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == HORIZONTAL_TYPE) {
            HORIZONTAL_TYPE
        } else PORTRAIT_TYPE
    }

    companion object {
        const val PORTRAIT_TYPE = 0
        const val HORIZONTAL_TYPE = 1
    }
}


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val newsImage: ImageView = itemView.findViewById(R.id.ivNewsImage)
    private val newsDate: TextView = itemView.findViewById(R.id.tvNewsDate)
    private val newsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
    val itemNews = itemView.findViewById<CardView>(R.id.cardView)
//    val search_card = itemView.findViewById<CardView>(R.id.search_card)

    fun bind(articles: PresentationArticles) {
        if (articles.urlToImage != null) {
            Picasso.get().load(articles.urlToImage).into(newsImage)
        }
        itemView.setOnClickListener {}
        newsDate.text = articles.publishedAt
        newsTitle.text = articles.title
    }


}