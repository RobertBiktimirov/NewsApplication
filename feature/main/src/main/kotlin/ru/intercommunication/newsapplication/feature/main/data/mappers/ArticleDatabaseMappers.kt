package ru.intercommunication.newsapplication.feature.main.data.mappers

import ru.intercommunication.newsapplication.core.localStorage.dto.ArticleLocalDto
import ru.intercommunication.newsapplication.core.localStorage.dto.ReminderTimeDto
import ru.intercommunication.newsapplication.core.localStorage.dto.SourceLocalDto
import ru.intercommunication.newsapplication.core.utils.Mapper
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.domain.models.ReminderTime
import ru.intercommunication.newsapplication.feature.main.domain.models.SourceModel
import javax.inject.Inject

class ArticleDatabaseMappers @Inject constructor() : Mapper<ArticleLocalDto, ArticleModel> {

    override fun map(from: ArticleLocalDto): ArticleModel {
        return ArticleModel(
            id = from.key,
            author = from.author,
            content = from.content,
            description = from.description,
            publishedAt = from.publishedAt,
            source = from.source.toModel(),
            title = from.title,
            url = from.url,
            urlToImage = from.urlToImage,
            isFavorite = from.isFavorite,
            comment = from.comment,
            reminder = when (from.reminder) {
                ReminderTimeDto.FIFTEEN_MINUTE -> ReminderTime.FIFTEEN_MINUTE
                ReminderTimeDto.HOUR -> ReminderTime.HOUR
                ReminderTimeDto.DAY -> ReminderTime.DAY
                ReminderTimeDto.SEVEN_DAY -> ReminderTime.SEVEN_DAY
                ReminderTimeDto.NOTHING -> ReminderTime.NOTHING
            }
        )
    }

    override fun reverseMap(from: ArticleModel): ArticleLocalDto {
        return ArticleLocalDto(
            key = from.id,
            author = from.author,
            content = from.content,
            description = from.description,
            publishedAt = from.publishedAt,
            source = from.source.toDto(),
            title = from.title,
            url = from.url,
            urlToImage = from.urlToImage,
            isFavorite = from.isFavorite,
            comment = from.comment,
            reminder = when (from.reminder) {
                ReminderTime.FIFTEEN_MINUTE -> ReminderTimeDto.FIFTEEN_MINUTE
                ReminderTime.HOUR -> ReminderTimeDto.HOUR
                ReminderTime.DAY -> ReminderTimeDto.DAY
                ReminderTime.SEVEN_DAY -> ReminderTimeDto.SEVEN_DAY
                ReminderTime.NOTHING -> ReminderTimeDto.NOTHING
            }
        )
    }

    @JvmName("toModel")
    private fun SourceLocalDto.toModel(): SourceModel = SourceModel(id, name)

    @JvmName("toDto")
    private fun SourceModel.toDto(): SourceLocalDto = SourceLocalDto(id, name)
}