package ru.intercommunication.newsapplication.feature.main.domain.models

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
)

data class SourceModel(
    val id: String?,
    val name: String
)
