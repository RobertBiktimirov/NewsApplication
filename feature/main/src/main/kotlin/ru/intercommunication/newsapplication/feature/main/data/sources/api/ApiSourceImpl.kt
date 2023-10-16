package ru.intercommunication.newsapplication.feature.main.data.sources.api

import ru.intercommunication.newsapplication.core.api.services.NewsService
import ru.intercommunication.newsapplication.feature.main.data.mappers.ArticleApiMapper
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(
    private val newsService: NewsService,
    private val apiMapper: ArticleApiMapper
) : ApiSource {

    companion object {
        private const val API_KEY = "b1ada83618e64d70911a1449698b15b9"
    }

    override suspend fun getNews(): List<ArticleModel> {
        return newsService.getNews(apiKey = API_KEY).articles.map { apiMapper.map(it) }
    }

    override suspend fun getNewsByQuery(query: String): List<ArticleModel> {
        return newsService.getNewsByQuery(query, API_KEY).articles.map { apiMapper.map(it) }
    }
}