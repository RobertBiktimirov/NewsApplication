package ru.intercommunication.newsapplication.feature.main.di.module

import dagger.Binds
import dagger.Module
import ru.intercommunication.newsapplication.feature.main.data.sources.local.DatabaseSource
import ru.intercommunication.newsapplication.feature.main.data.sources.local.DatabaseSourceImpl
import ru.intercommunication.newsapplication.feature.main.di.MainFeatureScope

@Module
internal interface DatabaseModule {

    @[Binds MainFeatureScope]
    fun bindDatabaseSource(impl: DatabaseSourceImpl): DatabaseSource
}