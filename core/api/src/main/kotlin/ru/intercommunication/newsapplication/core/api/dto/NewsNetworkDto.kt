package ru.intercommunication.newsapplication.core.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsNetworkDto(
    @SerialName("articles") val articles: List<ArticleNetrorkDto>,
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int
)