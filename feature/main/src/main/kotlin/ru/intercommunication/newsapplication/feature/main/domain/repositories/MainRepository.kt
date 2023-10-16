package ru.intercommunication.newsapplication.feature.main.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.intercommunication.newsapplication.core.utils.RepositoryResult
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel

interface MainRepository {

    suspend fun updateStateFavorite(isFavorite: Boolean, id: Int)

    suspend fun loadScreen(): RepositoryResult<Unit>

    fun getNews(): Flow<List<ArticleModel>>

    suspend fun getNewsByQuery(query: String): RepositoryResult<List<ArticleModel>>

    suspend fun saveNews(articleModel: ArticleModel): Int
}