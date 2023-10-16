package ru.intercommunication.newsapplication.feature.main.data.sources.api

import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel

interface ApiSource {

    suspend fun getNews(): List<ArticleModel>

    suspend fun getNewsByQuery(query: String): List<ArticleModel>
}