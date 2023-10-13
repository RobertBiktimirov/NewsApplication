package ru.intercommunication.newsapplication.feature.details.data.mappers

import ru.intercommunication.newsapplication.core.localStorage.dto.ArticleLocalDto
import ru.intercommunication.newsapplication.core.localStorage.dto.SourceLocalDto
import ru.intercommunication.newsapplication.core.utils.Mapper
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.models.SourceModel
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
            comment = from.comment
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
            comment = from.comment
        )
    }

    @JvmName("toModel")
    private fun SourceLocalDto.toModel(): SourceModel = SourceModel(id, name)

    @JvmName("toDto")
    private fun SourceModel.toDto(): SourceLocalDto = SourceLocalDto(id, name)
}