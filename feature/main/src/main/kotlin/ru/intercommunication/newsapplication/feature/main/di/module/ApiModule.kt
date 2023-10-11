package ru.intercommunication.newsapplication.feature.main.di.module

import dagger.Binds
import dagger.Module
import ru.intercommunication.newsapplication.feature.main.data.sources.api.ApiSource
import ru.intercommunication.newsapplication.feature.main.data.sources.api.ApiSourceImpl
import ru.intercommunication.newsapplication.feature.main.di.MainFeatureScope

@Module
internal interface ApiModule {


    @[Binds MainFeatureScope]
    fun bindApiSource(impl: ApiSourceImpl): ApiSource
}