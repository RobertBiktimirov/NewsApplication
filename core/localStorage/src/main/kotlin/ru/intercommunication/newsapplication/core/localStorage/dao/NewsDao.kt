package ru.intercommunication.newsapplication.core.localStorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.intercommunication.newsapplication.core.localStorage.dto.ArticleLocalDto
import ru.intercommunication.newsapplication.core.localStorage.dto.ReminderTimeDto

@Dao
interface NewsDao {

    @Query("select * from articles where `key` = :id limit 1")
    suspend fun getNewsItem(id: Int): ArticleLocalDto


    @Query("select `key` from articles where title = :title limit 1")
    suspend fun getNewsIdByTitle(title: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(articles: ArticleLocalDto)

    @Query("update articles set comment = :newComment where `key` = :key")
    suspend fun updateCommentNews(newComment: String, key: Int)

    @Query("update articles set reminder_time = :reminder where `key` = :key")
    suspend fun updateReminderNews(reminder: ReminderTimeDto, key: Int)

    @Query("select * from articles")
    fun getNewsFlow(): Flow<List<ArticleLocalDto>>

    @Query("select * from articles")
    suspend fun getNewsList(): List<ArticleLocalDto>

    @Query("update articles set isFavorite = :isFavorite where `key` = :id")
    suspend fun updateFavorite(isFavorite: Boolean, id: Int)
}