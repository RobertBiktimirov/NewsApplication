package ru.intercommunication.newsapplication.feature.main.di.dependencies

import android.content.Context
import androidx.annotation.RestrictTo
import ru.intercommunication.newsapplication.core.api.services.NewsService
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import kotlin.properties.Delegates

interface MainDependencies {
    @ApplicationContext
    val context: Context

    val newsDao: NewsDao

    val newsService: NewsService
}

interface MainDependenciesProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val dependencies: MainDependencies

    companion object : MainDependenciesProvider by MainDependenciesStore
}

object MainDependenciesStore : MainDependenciesProvider {
    override var dependencies: MainDependencies by Delegates.notNull()
}