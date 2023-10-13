package ru.intercommunication.newsapplication.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.intercommunication.newsapplication.core.api.services.NewsService
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import ru.intercommunication.newsapplication.di.modules.AppModule
import ru.intercommunication.newsapplication.di.modules.NetworkModule
import ru.intercommunication.newsapplication.di.modules.RoomModule
import ru.intercommunication.newsapplication.feature.details.di.dependencies.DetailsDependencies
import ru.intercommunication.newsapplication.feature.main.di.dependencies.MainDependencies
import javax.inject.Scope

@ApplicationScope
@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class])
interface AppComponent : MainDependencies, DetailsDependencies {

    @ApplicationContext
    override val context: Context

    override val newsDao: NewsDao

    override val newsService: NewsService

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@Scope
annotation class ApplicationScope