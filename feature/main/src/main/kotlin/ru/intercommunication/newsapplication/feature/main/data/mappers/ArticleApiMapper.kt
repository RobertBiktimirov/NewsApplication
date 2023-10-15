package ru.intercommunication.newsapplication.feature.main.data.mappers

import ru.intercommunication.newsapplication.core.api.dto.ArticleNetworkDto
import ru.intercommunication.newsapplication.core.api.dto.SourceNetworkDto
import ru.intercommunication.newsapplication.core.utils.OneWayMapper
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.domain.models.ReminderTime
import ru.intercommunication.newsapplication.feature.main.domain.models.SourceModel
import javax.inject.Inject

class ArticleApiMapper @Inject constructor(): OneWayMapper<ArticleNetworkDto, ArticleModel> {
    override fun map(from: ArticleNetworkDto): ArticleModel {
        return ArticleModel(
            id = 0,
            author = from.author ?: "",
            content = from.content ?: "",
            description = from.description ?: "",
            publishedAt = from.publishedAt,
            source = from.source.toModel(),
            title = from.title,
            url = from.url,
            urlToImage = from.urlToImage ?: "",
            isFavorite = false,
            comment = "",
            reminder = ReminderTime.NOTHING
        )
    }

    private fun SourceNetworkDto.toModel(): SourceModel = SourceModel(id, name)
}