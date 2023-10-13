package ru.intercommunication.newsapplication.feature.details.domain.repositories

import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel

interface DetailsRepository {
    suspend fun getNews(id: Int): ArticleModel

    suspend fun saveNewDataDetails(comment: String, isFavorite: Boolean, id: Int)
}