package ru.intercommunication.newsapplication.feature.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.intercommunication.newsapplication.feature.main.R
import ru.intercommunication.newsapplication.feature.main.databinding.ItemMainListBinding
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel

class NewsListAdapter(
    private val newsClickListener: (ArticleModel) -> Unit,
    private val favoriteClickListener: (isFavorite: Boolean, id: Int) -> Unit,
    private val setFavoriteAndSaveNews: ((ArticleModel) -> Unit)? = null
) : ListAdapter<ArticleModel, NewsListAdapter.NewsViewHolder>(MainDiffUtil()) {
    class NewsViewHolder(
        val binding: ItemMainListBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleModel: ArticleModel) {

            Glide.with(context)
                .load(articleModel.urlToImage)
                .placeholder(R.drawable.newspaper_img)
                .centerCrop()
                .into(binding.imgNews)

            binding.titleNews.text = articleModel.title

            binding.favoriteButton.setImageDrawable(
                if (articleModel.isFavorite) {
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.favorite_button
                    )
                } else {
                    AppCompatResources.getDrawable(
                        context, R.drawable.not_favorite_button
                    )
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainListBinding.inflate(inflater)
        val holder = NewsViewHolder(binding, parent.context)

        holder.binding.favoriteButton.setOnClickListener {
            val article = getItem(holder.adapterPosition)

            setFavoriteAndSaveNews?.let {
                it(article)
            } ?: run {
                favoriteClickListener(!article.isFavorite, article.id)
            }
        }

        holder.binding.imgNews.setOnClickListener {
            newsClickListener(getItem(holder.adapterPosition))
        }

        return holder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainDiffUtil : DiffUtil.ItemCallback<ArticleModel>() {
    override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem == newItem
    }
}