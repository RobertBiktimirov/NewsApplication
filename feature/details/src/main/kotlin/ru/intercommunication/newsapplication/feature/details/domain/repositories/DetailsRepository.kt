package ru.intercommunication.newsapplication.feature.details.domain.repositories

import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.models.ReminderTime

interface DetailsRepository {
    suspend fun getNews(id: Int): ArticleModel

    suspend fun saveCommentDetails(comment: String, id: Int)

    suspend fun saveReminder(reminderTime: ReminderTime, id: Int)
}