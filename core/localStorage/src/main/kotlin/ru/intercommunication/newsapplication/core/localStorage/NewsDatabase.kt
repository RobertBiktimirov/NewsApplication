package ru.intercommunication.newsapplication.core.localStorage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import ru.intercommunication.newsapplication.core.localStorage.dto.ArticleLocalDto

@Database(entities = [ArticleLocalDto::class], exportSchema = false, version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}