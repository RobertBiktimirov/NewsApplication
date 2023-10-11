package ru.intercommunication.newsapplication.core.api.services

import retrofit2.http.GET
import retrofit2.http.Query
import ru.intercommunication.newsapplication.core.api.dto.NewsNetworkDto

interface NewsService {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "ru",
        @Query("apiKey") apiKey: String,
    ): NewsNetworkDto

}