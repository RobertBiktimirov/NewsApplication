package ru.intercommunication.newsapplication.core.localStorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.intercommunication.newsapplication.core.localStorage.dto.ArticleLocalDto

@Dao
interface NewsDao {

    @Query("select * from articles where `key` = :id limit 1")
    suspend fun getNewsItem(id: Int): ArticleLocalDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(articles: ArticleLocalDto)

    @Query("delete from articles where `key` = :key")
    suspend fun deleteFavorite(key: Int)

    @Query("update articles set comment = :newComment where `key` = :key")
    suspend fun updateArticle(newComment: String,  key: Int)

    @Query("select * from articles")
    fun getFavoriteArticle(): Flow<List<ArticleLocalDto>>

    @Query("select * from articles")
    suspend fun getFavorite(): List<ArticleLocalDto>

    @Query("update articles set isFavorite = :isFavorite where `key` = :id")
    suspend fun updateFavorite(isFavorite: Boolean, id: Int)
}