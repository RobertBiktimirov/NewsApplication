package ru.intercommunication.newsapplication.feature.main.ui

import android.content.Context
import android.util.Log
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

class MainAdapter(
    private val newsClickListener: (ArticleModel) -> Unit,
    private val favoriteClickListener: (isFavorite: Boolean, id: Int) -> Unit
) : ListAdapter<ArticleModel, MainAdapter.MainViewHolder>(MainDiffUtil()) {
    class MainViewHolder(
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
                        R.drawable.turned
                    )
                } else {
                    AppCompatResources.getDrawable(
                        context, R.drawable.turned_in_not
                    )
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainListBinding.inflate(inflater)
        val holder = MainViewHolder(binding, parent.context)

        Log.d("recyclerViewTest", "holder -> $holder")

        holder.binding.favoriteButton.setOnClickListener {
            val article = getItem(holder.adapterPosition)
            favoriteClickListener(!article.isFavorite, article.id)
        }

        holder.binding.imgNews.setOnClickListener {
            newsClickListener(getItem(holder.adapterPosition))
        }

        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
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