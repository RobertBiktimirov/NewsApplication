package ru.intercommunication.newsapplication.feature.details.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import ru.intercommunication.newsapplication.core.localStorage.dto.ReminderTimeDto
import ru.intercommunication.newsapplication.feature.details.data.mappers.ArticleDatabaseMappers
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.models.ReminderTime
import ru.intercommunication.newsapplication.feature.details.domain.repositories.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val mapper: ArticleDatabaseMappers
) : DetailsRepository {

    override suspend fun getNews(id: Int): ArticleModel {
        return withContext(Dispatchers.IO) { return@withContext mapper.map(newsDao.getNewsItem(id)) }
    }

    override suspend fun saveCommentDetails(comment: String, id: Int) {
        withContext(Dispatchers.IO) {
            newsDao.updateCommentNews(comment, id)
        }
    }

    override suspend fun saveReminder(reminderTime: ReminderTime, id: Int) {
        withContext(Dispatchers.IO) {
            val reminderDto = when (reminderTime) {
                ReminderTime.FIFTEEN_MINUTE -> ReminderTimeDto.FIFTEEN_MINUTE
                ReminderTime.HOUR -> ReminderTimeDto.HOUR
                ReminderTime.DAY -> ReminderTimeDto.DAY
                ReminderTime.SEVEN_DAY -> ReminderTimeDto.SEVEN_DAY
                ReminderTime.NOTHING -> ReminderTimeDto.NOTHING
            }
            newsDao.updateReminderNews(reminderDto, id)
        }
    }
}