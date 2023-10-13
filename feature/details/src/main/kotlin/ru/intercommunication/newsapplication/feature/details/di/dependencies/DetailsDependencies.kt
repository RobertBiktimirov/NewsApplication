package ru.intercommunication.newsapplication.feature.details.di.dependencies

import android.content.Context
import androidx.annotation.RestrictTo
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.core.localStorage.dao.NewsDao
import kotlin.properties.Delegates

interface DetailsDependencies {
    @ApplicationContext
    val context: Context

    val newsDao: NewsDao
}

interface DetailsDependenciesProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val dependencies: DetailsDependencies

    companion object : DetailsDependenciesProvider by DetailsDependenciesStore
}

object DetailsDependenciesStore : DetailsDependenciesProvider {
    override var dependencies: DetailsDependencies by Delegates.notNull()
}