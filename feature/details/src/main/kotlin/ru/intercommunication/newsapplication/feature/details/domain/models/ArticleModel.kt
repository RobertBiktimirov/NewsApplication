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
    var comment: String = "",
    val reminder: ReminderTime
) : Parcelable {

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
                "",
                reminder = ReminderTime.NOTHING
            )
    }

}

@Parcelize
data class SourceModel(
    val id: String?,
    val name: String
) : Parcelable


enum class ReminderTime(val millisecond: Long) {
    FIFTEEN_MINUTE(1000 * 60 * 15),
    HOUR(1000 * 60 * 60),
    DAY(1000 * 60 * 60 * 24),
    SEVEN_DAY(1000 * 60 * 60 * 24 * 7),
    NOTHING(0L)
}
