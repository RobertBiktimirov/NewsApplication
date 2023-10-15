package ru.intercommunication.newsapplication.core.localStorage.dto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.intercommunication.newsapplication.core.localStorage.conventer.ReminderConverter

@Entity(tableName = "articles")
data class ArticleLocalDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("key") val key: Int,
    @ColumnInfo("author") val author: String,
    @ColumnInfo("content") val content: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("publishedAt") val publishedAt: String,
    @Embedded val source: SourceLocalDto,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("urlToImage") val urlToImage: String,
    @ColumnInfo("isFavorite") var isFavorite: Boolean = false,
    @ColumnInfo("comment") var comment: String = "",
    @TypeConverters(ReminderConverter::class)
    @ColumnInfo("reminder_time") val reminder: ReminderTimeDto = ReminderTimeDto.NOTHING
)

enum class ReminderTimeDto {
    FIFTEEN_MINUTE,
    HOUR,
    DAY,
    SEVEN_DAY,
    NOTHING
}