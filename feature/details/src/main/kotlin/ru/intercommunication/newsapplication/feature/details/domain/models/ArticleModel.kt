package ru.intercommunication.newsapplication.feature.details.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel(
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceModel,
    val title: String,
    val url: String,
    val urlToImage: String,
    var isFavorite: Boolean = false,
    var comment: String = ""
): Parcelable {

    companion object {
        fun toEmpty() =
            ArticleModel(
                0,
                "",
                "",
                "",
                "",
                SourceModel(null, ""),
                "",
                "",
                ""
            )
    }

}
@Parcelize
data class SourceModel(
    val id: String?,
    val name: String
): Parcelable
