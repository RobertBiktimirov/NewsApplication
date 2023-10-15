package ru.intercommunication.newsapplication.feature.details.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import ru.intercommunication.newsapplication.feature.details.data.mappers.ArticleDatabaseMappers
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.repositories.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val mapper: ArticleDatabaseMappers
) : DetailsRepository {

    override suspend fun getNews(id: Int): ArticleModel {
        return withContext(Dispatchers.IO) { return@withContext mapper.map(newsDao.getNewsItem(id)) }
    }

    override suspend fun saveNewDataDetails(comment: String, id: Int) {
        return withContext(Dispatchers.IO) {
            newsDao.updateArticle(comment, id)
        }
    }
}