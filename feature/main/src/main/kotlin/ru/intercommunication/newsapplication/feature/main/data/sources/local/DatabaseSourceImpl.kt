package ru.intercommunication.newsapplication.feature.main.data.sources.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import ru.intercommunication.newsapplication.feature.main.data.mappers.ArticleDatabaseMappers
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import javax.inject.Inject

class DatabaseSourceImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val articleDatabaseMappers: ArticleDatabaseMappers
) : DatabaseSource {

    override suspend fun updateStateFavorite(isFavorite: Boolean, id: Int) {
        newsDao.updateFavorite(isFavorite, id)
    }

    override suspend fun saveNews(newList: MutableList<ArticleModel>) {
        val oldList = newsDao.getFavorite().map { articleDatabaseMappers.map(it) }
        for (new in newList) {
            if (new.title !in oldList.map { it.title }) {
                newsDao.saveArticle(articleDatabaseMappers.reverseMap(new))
            }
        }
    }

    override fun getNews(): Flow<List<ArticleModel>> {
        return newsDao.getFavoriteArticle()
            .map { list -> list.map { articleDatabaseMappers.map(it) }.reversed() }
    }
}