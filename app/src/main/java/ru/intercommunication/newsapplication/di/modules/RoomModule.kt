package ru.intercommunication.newsapplication.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.core.localStorage.NewsDatabase
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import ru.intercommunication.newsapplication.di.ApplicationScope

@Module
class RoomModule {

    @[Provides ApplicationScope]
    fun provideAppDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_database.db"
        ).build()


    @[Provides ApplicationScope]
    fun provideGamesDao(database: NewsDatabase): NewsDao = database.newsDao()

}