package ru.intercommunication.newsapplication.feature.main.data.sources.local

import kotlinx.coroutines.flow.Flow
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel

interface DatabaseSource {

    suspend fun updateStateFavorite(isFavorite: Boolean, id: Int)

    suspend fun saveNews(newList: MutableList<ArticleModel>)

    fun getNews(): Flow<List<ArticleModel>>
}